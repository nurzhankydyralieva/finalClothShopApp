DROP TABLE IF EXISTS categories;

CREATE TABLE categories
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    products VARCHAR(100)
);