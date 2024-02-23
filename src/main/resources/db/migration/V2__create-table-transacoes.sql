CREATE TYPE tipo_transacao AS ENUM ('DEBITO', 'CREDITO');
CREATE TABLE tb_transacoes (
                               id BIGINT SERIAL PRIMARY KEY,
                               valor DOUBLE PRECISION,
                               tipo tipo_transacao,
                               descricao VARCHAR(255),
                               realizada_em TIMESTAMP,
                               cliente_id BIGINT REFERENCES tb_cliente(id)
);