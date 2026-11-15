create table student
(
    id         bigint auto_increment
        primary key,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    email      varchar(255) not null,
    department varchar(100) null,
    level      int          null
);