package com.cargotransportation.services.impl;

import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.converter.Converter;
import com.cargotransportation.dao.*;
import com.cargotransportation.dto.OrderDto;
import com.cargotransportation.dto.requests.CreateAddressRequest;
import com.cargotransportation.dto.requests.CreateOrderRequest;
import com.cargotransportation.dto.response.OrderPriceInfoResponse;
import com.cargotransportation.exception.AuthException;
import com.cargotransportation.exception.IllegalStatusException;
import com.cargotransportation.exception.IsExistsException;
import com.cargotransportation.exception.NotFoundException;
import com.cargotransportation.repositories.OrderRepository;
import com.cargotransportation.repositories.TransportRepository;
import com.cargotransportation.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ProductTypeService productTypeService;
    private final UserService userService;
    private final TransportRepository transportRepository;
    private final AuthService authService;


    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto create(CreateOrderRequest request) {
        Address sourceAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getSourceAddress())
                        .state(request.getSourceState())
                        .latitude(request.getSourceLatitude())
                        .longitude(request.getSourceLongitude())
                        .city(request.getSourceCity())
                        .build()));
        log.info("Source address created: " + sourceAddress);

        Address destinationAddress = Converter.convert(addressService.save(
                CreateAddressRequest.builder()
                        .address(request.getDestinationAddress())
                        .state(request.getDestinationState())
                        .city(request.getDestinationCity())
                        .latitude(request.getDestinationLatitude())
                        .longitude(request.getDestinationLongitude())
                        .build()));
        log.info("Destination address created: " + destinationAddress);

        ProductType productType = Converter.convert(productTypeService.findByName(request.getProductType()));
        log.info("Product type found: " + productType);

        Order order = Order.builder()
                .shipper(Converter.convert(userService.findUserByRoleAndId("ROLE_SHIPPER",request.getShipperId())))
                .sourceAddress(sourceAddress)
                .carrier(null)
                .broker(null)
                .currentLocation(null)
                .status(OrderStatus.WAITING)
                .createdDate(LocalDateTime.now())
                .destinationAddress(destinationAddress)
                .volume(request.getVolume())
                .productType(productType)
                .estimatedDeliveryDate(request.getEstimatedDeliveryDate())
                .totalKm(request.getTotalKm())
                .duration(request.getDuration())
                .orderType(request.getOrderType())
                .build();
        orderRepository.saveAndFlush(order);

        return Converter.convert(order);
    }
    @Override
//    @PreAuthorize("hasAuthority('order.read')")
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException(
                "Order with id '" + id + "' not found!"
        ));
        return Converter.convert(order);
    }

    @Override
    public OrderPriceInfoResponse getPriceInfoByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));

        User carrier = order.getCarrier();
        if(carrier != null){
            double totalPrice;
            CarrierCompany carrierCompany = carrier.getTransport().getCarrierCompany();
            if(carrierCompany == null){
                throw new NotFoundException("Carrier with id '" + carrier.getId() + "' does not have a company!");
            }
            double totalKmPrice = carrierCompany.getPricePerKm() * (order.getTotalKm()==null?1.0:order.getTotalKm());
            double totalVolumePrice = (carrierCompany.getPricePerLb() * (order.getVolume()==null?1.0:order.getVolume()));
            int percent = order.getOrderType()==OrderType.EXPRESS?carrierCompany.getPercentToExpress():carrierCompany.getPercentToStandard();
            totalPrice = totalKmPrice + totalVolumePrice;
            double totalOrderTypePrice = order.getOrderType()==OrderType.EXPRESS?
                    (carrierCompany.getPercentToExpress()*totalPrice/100)
                    :
                    (carrierCompany.getPercentToStandard()*totalPrice/100);

            totalPrice +=  totalOrderTypePrice;
            int[] minutesAndHours = parseDuration(order.getDuration());
            return OrderPriceInfoResponse.builder()
                    .totalDistancePrice(totalKmPrice)
                    .totalVolumePrice(totalVolumePrice)
                    .totalByOrderType(totalOrderTypePrice)
                    .tariffForDistance(carrierCompany.getPricePerKm())
                    .tariffForVolume(carrierCompany.getPricePerLb())
                    .percentByOrderType(percent)
                    .hours(minutesAndHours[0])
                    .km(order.getTotalKm())
                    .volume(order.getVolume())
                    .minutes(minutesAndHours[1])
                    .total(totalPrice)
                    .build();

        }

        return null;
    }

    @Override
    public List<OrderDto> getAllConfirmedOrders() {
        return orderRepository.findByStatus(OrderStatus.CONFIRMED)
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByShipper() {
        User shipper = Converter.convert(userService.findById(
                userService.findByUsername(
                        authService.getCurrentUserUsername()
                ).getId())
        );
        if(!shipper.getRole().getName().equals("ROLE_SHIPPER")){
            throw new AuthException("User is not shipper!", HttpStatus.OK);
        }
        return orderRepository.findAllByShipperId(shipper.getId())
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByCarrierAndStatus(OrderStatus status) {
        User carrier = Converter.convert(userService.findById(
                userService.findByUsername(
                        authService.getCurrentUserUsername()
                ).getId())
        );

        return orderRepository.findAllByCarrierAndStatus(carrier,status)
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());
    }

    private int[] parseDuration(String duration){
        String[] durationParts = duration.split(":");
        int hours = Integer.parseInt(durationParts[0]);
        int minutes = Integer.parseInt(durationParts[1]);

        return new int[]{hours,minutes};
    }
    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto takeByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));

        isOrderRejected(order);

        if(order.getCarrier() != null) throw new IsExistsException(
                "Order with id '" + orderId + "' has already been taken",
                HttpStatus.BAD_REQUEST
        );

        User carrier = Converter.convert(userService.findById(
                userService.findByUsername(
                        authService.getCurrentUserUsername()
                ).getId()
        ));
        order.setCarrier(carrier);

        double totalPrice;
        Transport transport = transportRepository.findByCarrier(carrier);
        if (transport == null) {
            throw new NotFoundException("Carrier with id' " + carrier.getId() + "' has not transport!");
        }

        CarrierCompany carrierCompany = transport.getCarrierCompany();

        double totalKmPrice = carrierCompany.getPricePerKm() * (order.getTotalKm()==null?1.0:order.getTotalKm());
        double totalVolumePrice = (carrierCompany.getPricePerLb() * (order.getVolume()==null?1.0:order.getVolume()));
        int percent = order.getOrderType()==OrderType.EXPRESS?carrierCompany.getPercentToExpress():carrierCompany.getPercentToStandard();
        totalPrice = totalKmPrice + totalVolumePrice;
        double totalOrderTypePrice = order.getOrderType()==OrderType.EXPRESS?
                (carrierCompany.getPercentToExpress()*totalPrice/100)
                :
                (carrierCompany.getPercentToStandard()*totalPrice/100);

        totalPrice +=  totalOrderTypePrice;

        order.setTotalPrice(totalPrice);


        order.setStatus(OrderStatus.TAKEN);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
    public OrderDto confirmByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        isOrderRejected(order);

        if(order.getStatus() != OrderStatus.TAKEN)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not took!",
                    HttpStatus.CONFLICT);
        }

        order.setStatus(OrderStatus.CONFIRMED);
        return Converter.convert(orderRepository.save(order));
    }


    @Override
    public OrderDto declineByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        isOrderRejected(order);

        if(order.getStatus() != OrderStatus.TAKEN)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not took!",
                    HttpStatus.CONFLICT);
        }

        order.setStatus(OrderStatus.REJECTED);
        order.setCarrier(null);
        return Converter.convert(orderRepository.save(order));
    }

    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto acceptByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        isOrderRejected(order);
        User user = Converter.convert(userService.findById(
                userService.findByUsername(
                        authService.getCurrentUserUsername()
                ).getId())
        );
        if(order.getStatus() != OrderStatus.CONFIRMED)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not confirmed by shipper!",
                    HttpStatus.CONFLICT);
        }
        order.setStatus(OrderStatus.ACCEPTED);
        order.setBroker(user);
        return Converter.convert(orderRepository.save(order));
    }

    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto setProductTakenDateById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        isOrderRejected(order);
        if(order.getStatus() != OrderStatus.ACCEPTED)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not accepted!",
                    HttpStatus.CONFLICT);
        }
        order.setTakenDate(LocalDateTime.now());
        order.setStatus(OrderStatus.SHIPPED);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto setDeliveredDateById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        isOrderRejected(order);
        if(order.getStatus() != OrderStatus.SHIPPED)
        {
            throw new IllegalStatusException(
                    "Order with id '" + orderId + " is not shipped!",
                    HttpStatus.CONFLICT);
        }
        order.setDeliveredDate(LocalDateTime.now());
        order.setStatus(OrderStatus.DELIVERED);

        return Converter.convert(orderRepository.save(order));
    }

    @Override
//    @PreAuthorize("hasAuthority('order.edit')")
    public OrderDto rejectById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException(
                "Order with id '" + orderId + "' not found!"
        ));
        if(order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStatusException(
                    "Order is not confirmed!",
                    HttpStatus.CONFLICT
            );
        }
        order.setStatus(OrderStatus.REJECTED);

        return Converter.convert(orderRepository.save(order));
    }


    @Override
//    @PreAuthorize("hasAuthority('order.read')")
    public List<OrderDto> findAllByStatus(OrderStatus status) {
        return orderRepository.findAll().stream().map(Converter::convert).collect(Collectors.toList());
    }



    @Override
//    @PreAuthorize("hasAuthority('order.read')")
    public List<OrderDto> findAllWithFilter(
            String sourceCity, String sourceState, String destinationCity, String destinationState,
            Integer minVolume, Integer maxVolume,
            String productTypeName,
            LocalDateTime minDate, LocalDateTime maxDate
    ) {
        ProductType productType = productTypeName==null?null:Converter.convert(productTypeService.findByName(productTypeName));
        return orderRepository.filterOrders(
                        sourceCity, sourceState,
                        destinationCity,destinationState,
                        minVolume, maxVolume,
                        productType,
                        minDate, maxDate
                )
                .stream()
                .map(Converter::convert)
                .collect(Collectors.toList());

    }


    private void isOrderRejected(Order o){
        if(o.getStatus() == OrderStatus.REJECTED){
            throw new IllegalStatusException(
                    "Order with id '" + o.getId() + " is rejected!",
                    HttpStatus.CONFLICT
            );
        }
    }

}