DROP TABLE IF EXISTS vendor;

CREATE TABLE vendor
(
    id   BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
    name VARCHAR(100)
);