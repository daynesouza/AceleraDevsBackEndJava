create table clients(

    cpf varchar(11) not null,
    name varchar(100) not null,
    dta DATE,
    cep varchar(9) not null,
    status Boolean not null,

    primary key(cpf)
);
