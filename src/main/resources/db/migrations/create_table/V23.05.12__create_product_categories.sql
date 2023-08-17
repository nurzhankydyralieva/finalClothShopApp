DROP TABLE IF EXISTS product_categories;

CREATE TABLE product_categories
(
    categories_id BIGINT NOT NULL,
    product_id    BIGINT NOT NULL,
    PRIMARY KEY (categories_id, product_id),
    FOREIGN KEY (categories_id) REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE

);