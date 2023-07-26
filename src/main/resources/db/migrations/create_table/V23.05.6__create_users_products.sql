DROP TABLE IF EXISTS users_products;

CREATE TABLE users_products
(
    products_id BIGINT NOT NULL,
    user_id    UUID NOT NULL,
    PRIMARY KEY (products_id, user_id),
    FOREIGN KEY (products_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE

);
