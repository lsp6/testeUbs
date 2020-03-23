create database TesteUbs
go

USE TesteUbs
GO

create table products (id bigint identity not null, file_name varchar(255) not null, industry varchar(255) not null, origin varchar(255) not null, price double precision not null, product varchar(255) not null, quantity int not null, type varchar(255) not null, primary key (id))
alter table products add constraint UKt12clcjt6bxyyobd80804gvi8 unique (product, quantity, price, type, industry, origin, file_name)
