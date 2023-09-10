DROP TABLE IF EXISTS _user_orders;

CREATE TABLE _user_orders
(
    user_id   UUID NOT NULL,
    orders_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, orders_id),
    FOREIGN KEY (user_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (orders_id) REFERENCES _order (id) ON DELETE CASCADE ON UPDATE CASCADE

);