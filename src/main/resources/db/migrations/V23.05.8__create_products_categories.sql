DROP TABLE IF EXISTS products_categories;

CREATE TABLE products_categories
(
    categories_id BIGINT NOT NULL,
    product_id    BIGINT NOT NULL,
    PRIMARY KEY (categories_id, product_id),
    FOREIGN KEY (categories_id) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE

);