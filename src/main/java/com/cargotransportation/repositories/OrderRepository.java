package com.cargotransportation.repositories;

import com.cargotransportation.constants.AddressType;
import com.cargotransportation.constants.OrderStatus;
import com.cargotransportation.dao.Order;
import com.cargotransportation.dao.ProductType;
import com.cargotransportation.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByStatus(OrderStatus status);

    @Query(value = "SELECT * FROM orders WHERE shipper_id = :shipperId", nativeQuery = true)
    List<Order> findAllByShipperId(@Param("shipperId") Long shipperId);

    @Query("SELECT o FROM Order o " +
            "WHERE (:sourceCity IS NULL OR o.sourceAddress.city = :sourceCity) " +
            "AND (:sourceState IS NULL OR o.sourceAddress.state = :sourceState) "+
            "AND (:destinationState IS NULL OR o.destinationAddress.state = :destinationState) "+
            "AND (:destinationCity IS NULL OR o.destinationAddress.city = :destinationCity) " +
            "AND (:minVolume IS NULL OR o.volume >= :minVolume) " +
            "AND (:maxVolume IS NULL OR o.volume <= :maxVolume) " +
            "AND (:productType IS NULL OR o.productType = :productType) " +
            "AND (cast(:minDate as timestamp) IS NULL OR o.estimatedDeliveryDate >= :minDate) " +
            "AND (cast(:maxDate as timestamp) IS NULL OR o.estimatedDeliveryDate <= :maxDate)"
    )
    List<Order> filterOrders(
            @Param("sourceCity") String sourceCity,
            @Param("sourceState") String sourceState,
            @Param("destinationCity") String destinationCity,
            @Param("destinationState") String destinationState,
            @Param("minVolume") Integer minVolume,
            @Param("maxVolume") Integer maxVolume,
            @Param("productType") ProductType productType,
            @Param("minDate") LocalDateTime minDate,
            @Param("maxDate") LocalDateTime maxDate
            );
}
