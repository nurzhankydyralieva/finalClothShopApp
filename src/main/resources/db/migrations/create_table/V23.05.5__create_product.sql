DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id           BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(40) NOT NULL,
    price        INT         NOT NULL,
    quantity     INT         NOT NULL,
    order_id     BIGINT,
    vendor_id    BIGINT,
    _user_id      UUID

);