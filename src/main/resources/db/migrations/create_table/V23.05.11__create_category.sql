DROP TABLE IF EXISTS category;

CREATE TABLE category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    product_id BIGINT


);