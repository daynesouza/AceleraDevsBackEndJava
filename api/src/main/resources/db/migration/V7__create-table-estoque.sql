create table estoque (

    id int auto_increment,
    data_referencia DATE not null,
    int quantidade not null,
    produto_cod varchar(11) not null,
    primary key(id),

    constraint fk_itemPedido_produto_cod foreign key(produto_cod) references products(cod)
);
