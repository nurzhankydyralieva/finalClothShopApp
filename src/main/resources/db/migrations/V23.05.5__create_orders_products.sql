DROP TABLE IF EXISTS orders_products;

CREATE TABLE orders_products
(
    order_id   BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, products_id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (products_id) REFERENCES products (id)
);