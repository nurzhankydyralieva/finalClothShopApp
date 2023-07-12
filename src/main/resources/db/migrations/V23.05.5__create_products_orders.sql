DROP TABLE IF EXISTS products_orders;

CREATE TABLE products_orders
(
    orders_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (orders_id, product_id),
    FOREIGN KEY (orders_id) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE

);