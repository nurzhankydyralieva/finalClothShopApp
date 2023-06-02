DROP TABLE IF EXISTS categories_products;

CREATE TABLE categories_products
(
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    PRIMARY KEY (category_id, product_id),
    FOREIGN KEY (category_id) REFERENCES categories (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);