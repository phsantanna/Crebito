create table tb_cliente
(
    id            SERIAL PRIMARY KEY,
    limite        double precision not null,
    saldo_inicial double precision not null
);
