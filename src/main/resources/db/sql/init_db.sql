
    create table addresses (
        latitude float(53),
        longitude float(53),
        id bigserial not null,
        address varchar(255),
        city varchar(255),
        state varchar(255),
        type varchar(255) check (type in ('CURRENT','SOURCE','DESTINATION')),
        primary key (id)
    );

    create table agents (
        percent_to_express integer,
        percent_to_standard integer,
        price_per_km integer,
        price_per_lb integer,
        id bigint not null,
        company_address varchar(255),
        company_name varchar(255),
        primary key (id)
    );

    create table carriers (
        agent_id integer,
        id bigint not null,
        primary key (id)
    );

    create table documents (
        id bigserial not null,
        order_id integer,
        user_id integer,
        format varchar(255),
        name varchar(255),
        type varchar(255) check (type in ('CV','PASSPORT','DRIVERS_LICENSES','INSURANCE','TECH_PASSPORT')),
        primary key (id)
    );

    create table orders (
        distance float(53),
        total_price float(53),
        volume integer,
        broker_id bigint,
        carrier_id bigint,
        created_date TIMESTAMP,
        current_location_id bigint unique,
        delivered_date TIMESTAMP,
        destination_address_id bigint unique,
        estimated_delivery_date TIMESTAMP,
        id bigserial not null,
        price bigint,
        shipper_id bigint,
        source_address_id bigint unique,
        taken_date TIMESTAMP,
        duration varchar(255),
        name varchar(255),
        status varchar(255) check (status in ('WAITING','TAKEN','CONFIRMED','ACCEPTED','SHIPPED','DELIVERED','REJECTED')),
        type varchar(255) check (type in ('STANDARD','EXPRESS')),
        type_id varchar(255),
        primary key (id)
    );

    create table permissions (
        name varchar(255) not null,
        primary key (name)
    );

    create table product_types (
        name varchar(255) not null,
        primary key (name)
    );

    create table roles (
        name varchar(255) not null,
        primary key (name)
    );

    create table roles_permissions (
        permission_id varchar(255) not null,
        role_id varchar(255) not null,
        primary key (permission_id, role_id)
    );

    create table transports (
        capacity integer,
        agent_id bigint,
        carrier_id bigint unique,
        id bigserial not null,
        model varchar(255),
        number varchar(255),
        type varchar(255) check (type in ('TRUCK','PASSENGER_CAR')),
        primary key (id)
    );

    create table users (
        birth_date date,
        is_confirmed boolean,
        created_at timestamp(6),
        id bigserial not null,
        address varchar(255),
        fio varchar(255),
        password varchar(255),
        phone varchar(255),
        role varchar(255),
        username varchar(255) unique,
        primary key (id)
    );

    alter table if exists agents 
       add constraint FKqaw7vqyrwpqych5yeuwyjcyjs 
       foreign key (id) 
       references users;

    alter table if exists carriers 
       add constraint FKshubcnwtfbihsspu2ciehpveg 
       foreign key (agent_id) 
       references agents;

    alter table if exists carriers 
       add constraint FKc5y4482qvckt44i6rthl6a6x2 
       foreign key (id) 
       references users;

    alter table if exists documents 
       add constraint FKpa2ye2pqmpd55sy72g6ho2hbr 
       foreign key (order_id) 
       references orders;

    alter table if exists documents 
       add constraint FKkxttj4tp5le2uth212lu49vny 
       foreign key (user_id) 
       references users;

    alter table if exists orders 
       add constraint FKk6ql5aqbb1c1qjsk22bhotfxv 
       foreign key (broker_id) 
       references users;

    alter table if exists orders 
       add constraint FKb47jiphk85fc69thu4g3ujq7f 
       foreign key (carrier_id) 
       references users;

    alter table if exists orders 
       add constraint FK7ew63b14w8j2wq3daldcdgbx0 
       foreign key (current_location_id) 
       references addresses;

    alter table if exists orders 
       add constraint FKnjq7xrupks4ys8g5mmtkchlqg 
       foreign key (destination_address_id) 
       references addresses;

    alter table if exists orders 
       add constraint FKm1hb2iybrajm11caj5f8nce5l 
       foreign key (type_id) 
       references product_types;

    alter table if exists orders 
       add constraint FKmjnf91amspoh2m4qg8ct5y8uj 
       foreign key (shipper_id) 
       references users;

    alter table if exists orders 
       add constraint FK8b39epmo9mapvq405ynbsida2 
       foreign key (source_address_id) 
       references addresses;

    alter table if exists roles_permissions 
       add constraint FKbx9r9uw77p58gsq4mus0mec0o 
       foreign key (permission_id) 
       references permissions;

    alter table if exists roles_permissions 
       add constraint FKqi9odri6c1o81vjox54eedwyh 
       foreign key (role_id) 
       references roles;

    alter table if exists transports 
       add constraint FKlv4tu9vlg15w63rl1v78kf4fj 
       foreign key (agent_id) 
       references agents;

    alter table if exists transports 
       add constraint FKrm2pxhumjebm7x60q5fsihlit 
       foreign key (carrier_id) 
       references users;

    alter table if exists users 
       add constraint FK4c6vlshk8x83ifeoggi3exg3k 
       foreign key (role) 
       references roles;
