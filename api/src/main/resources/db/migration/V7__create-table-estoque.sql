create table estoque (

    id int auto_increment,
    produto_cod varchar(11) not null,
    data_referencia DATE not null,
    quantidade int not null,
    primary key(id),

    constraint fk_estoque_produto_cod foreign key(produto_cod) references products(cod)
);
