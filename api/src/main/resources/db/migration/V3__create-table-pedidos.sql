create table pedidos (

    id int auto_increment,
    valor_total DECIMAL not null,
    data_compra DATE not null,
    client_cpf varchar(11) not null,
    primary key(id),

    constraint fk_pedido_client_cpf foreign key(client_cpf) references clients(cpf)
);
