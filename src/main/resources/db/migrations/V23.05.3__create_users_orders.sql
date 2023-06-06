DROP TABLE IF EXISTS users_orders;

CREATE TABLE users_orders
(
    user_id  BIGINT NOT NULL,
    order_id BIGINT    NOT NULL,
    PRIMARY KEY (user_id, order_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);