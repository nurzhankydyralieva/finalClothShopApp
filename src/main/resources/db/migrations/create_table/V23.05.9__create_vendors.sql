DROP TABLE IF EXISTS vendors;

CREATE TABLE vendors
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
--     products_id BIGINT
);