DROP TABLE IF EXISTS products;

CREATE TABLE products
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(40) NOT NULL,
    price       INT         NOT NULL,
    quantity    INT         NOT NULL,
    category_id BIGINT
);