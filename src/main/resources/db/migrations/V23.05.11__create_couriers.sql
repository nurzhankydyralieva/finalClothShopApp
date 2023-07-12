DROP TABLE IF EXISTS couriers;

CREATE TABLE couriers
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    phone_number  VARCHAR(40) NOT NULL,
    id_number  VARCHAR(40) NOT NULL
);
