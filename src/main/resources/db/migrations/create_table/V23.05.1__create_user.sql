DROP TABLE IF EXISTS _user;

CREATE TABLE _user
(
    id          UUID PRIMARY KEY,
    user_name   VARCHAR(40) NOT NULL,
    first_name  VARCHAR(40) NOT NULL,
    last_name   VARCHAR(40) NOT NULL,
    age INTEGER,
    email       VARCHAR(50) UNIQUE,
    phone       VARCHAR(255),
    password    VARCHAR(255),
    status      VARCHAR(50),
    role        VARCHAR(40),
    orders_id   BIGINT,
    products_id BIGINT

);