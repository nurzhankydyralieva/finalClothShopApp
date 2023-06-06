DROP TABLE IF EXISTS categories_products;

CREATE TABLE categories_products
(
    category_id BIGINT NOT NULL,
    products_id  BIGINT NOT NULL,
    PRIMARY KEY (category_id, products_id),
    FOREIGN KEY (category_id) REFERENCES categories (id),
    FOREIGN KEY (products_id) REFERENCES products (id)
);