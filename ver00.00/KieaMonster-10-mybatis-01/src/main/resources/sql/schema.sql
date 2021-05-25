drop table if exists products;

create table products
(
	prod_id        identity       primary key,
	prod_name      varchar(255)   not null,
	prod_price     int            not null
);