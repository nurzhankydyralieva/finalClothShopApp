DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    user_name  VARCHAR(40) NOT NULL UNIQUE,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    email      VARCHAR(50) UNIQUE,
    phone      VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(40)

);