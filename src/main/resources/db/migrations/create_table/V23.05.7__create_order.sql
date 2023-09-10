DROP TABLE IF EXISTS _order;

CREATE TABLE _order
(
    id         BIGSERIAL PRIMARY KEY,
    ship_date  DATE NOT NULL,
    created_at DATE,
    complete   BOOLEAN,
    status     VARCHAR(50),
    product_id      BIGINT,
    _user_id      UUID
);