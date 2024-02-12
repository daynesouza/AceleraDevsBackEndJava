create table item_pedido(

    id int auto_increment not null,
    valor DECIMAL not null,
    quantidade int not null,
    pedido_id bigint not null,
    produto_cod varchar(100) not null,

    primary key(id),

    constraint fk_itemPedido_pedido_id foreign key(pedido_id) references pedidos(id),
    constraint fk_itemPedido_produto_cod foreign key(produto_cod) references products(cod)
);