DROP TABLE IF EXISTS _user_products;

CREATE TABLE _user_products
(
    products_id BIGINT NOT NULL,
    user_id    UUID NOT NULL,
    PRIMARY KEY (products_id, user_id),
    FOREIGN KEY (products_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE

);
