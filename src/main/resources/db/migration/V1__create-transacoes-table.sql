CREATE TYPE tipo_transactions as ENUM('DEBITO','CREDITO');
CREATE TABLE tb_transacoes (
                               id SERIAL PRIMARY KEY,
                               valor DOUBLE PRECISION NOT NULL,
                               tipo tipo_transactions NOT NULL,
                               descricao VARCHAR(255) NOT NULL,
                               realizada_em TIMESTAMP NOT NULL,
                               cliente_id BIGINT
);
