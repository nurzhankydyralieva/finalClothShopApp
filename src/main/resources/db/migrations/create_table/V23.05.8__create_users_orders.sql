DROP TABLE IF EXISTS users_orders;

CREATE TABLE users_orders
(
    user_id   UUID NOT NULL,
    orders_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, orders_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (orders_id) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE

);