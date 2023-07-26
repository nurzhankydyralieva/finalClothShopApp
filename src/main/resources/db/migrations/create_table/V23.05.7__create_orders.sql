DROP TABLE IF EXISTS orders;

CREATE TABLE orders
(
    id         BIGSERIAL PRIMARY KEY,
    ship_date  DATE NOT NULL,
    created_at DATE,
    complete   BOOLEAN,
    status     VARCHAR(50),
    product_id      BIGINT,
    user_id      UUID
);