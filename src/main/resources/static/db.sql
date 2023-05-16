CREATE TABLE booking_office (
    office_id BIGINT NOT NULL,
    end_contract_deadline DATE,
    office_name VARCHAR(50),
    office_phone VARCHAR(15),
    office_place VARCHAR(50),
    office_price BIGINT,
    start_contract_deadline DATE,
    trip_id BIGINT,
    primary key (office_id)
);

create table car (
    license_plate varchar(50) not null,
    car_color varchar(11),
    car_type varchar(11),
    company varchar(50),
    park_id bigint,
    primary key (license_plate)
);

create table employee (
    employee_id bigint not null auto_increment,
    department varchar(10),
    employee_address varchar(50),
    employee_birthday date,
    employee_email varchar(50),
    employee_name varchar(50),
    employee_phone varchar(15),
    password varchar(20),
    sex varchar(10),
    username varchar(50),
    primary key (employee_id)
);

create table parking_lot (
    park_id bigint not null auto_increment,
    park_area bigint,
    park_name varchar(50),
    park_place varchar(11),
    park_price bigint,
    park_status varchar(50),
    primary key (park_id)
);

create table ticket (
    ticket_id bigint not null,
    booking_time time,
    customer_name varchar(11),
    trip_id bigint,
    primary key (ticket_id)
);

create table trip (
    trip_id bigint not null auto_increment,
    booked_ticket_number integer,
    car_type varchar(11),
    departure_date date,
    departure_time time,
    destination varchar(50),
    driver varchar(50),
    maximum_online_ticket_number integer,
    primary key (trip_id)
);

alter table booking_office
    add constraint FK_bookingOffice_trip
        foreign key (trip_id) references trip (trip_id);

alter table car
    add constraint FK_car_parkingLot
        foreign key (park_id) references parking_lot (park_id);

alter table ticket
    add constraint FK_ticket_trip
        foreign key (trip_id) references trip (trip_id);

alter table booking_office modify office_id BIGINT NOT NULL AUTO_INCREMENT;

alter table ticket modify ticket_id BIGINT NOT NULL AUTO_INCREMENT;