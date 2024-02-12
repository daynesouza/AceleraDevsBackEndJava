create table products(

    cod varchar(100) not null,
    name varchar(100) not null,
    price DECIMAL not null,
    status Boolean not null,

    primary key(cod)
);