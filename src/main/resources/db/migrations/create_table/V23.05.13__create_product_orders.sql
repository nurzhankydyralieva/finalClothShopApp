DROP TABLE IF EXISTS product_orders;

CREATE TABLE product_orders
(
    orders_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (orders_id, product_id),
    FOREIGN KEY (orders_id) REFERENCES _order (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE

);