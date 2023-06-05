create sequence hibernate_sequence start 1 increment 1;
create table client
(
    id           int8 not null,
    bank_id      int8,
    birth_date   timestamp,
    birth_place  varchar(255),
    email        varchar(255),
    first_name   varchar(255),
    last_name    varchar(255),
    live_address varchar(255),
    passport     varchar(255),
    phone        varchar(255),
    reg_address  varchar(255),
    third_name   varchar(255),
    primary key (id)
);