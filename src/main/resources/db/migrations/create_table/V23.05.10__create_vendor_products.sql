DROP TABLE IF EXISTS vendor_products;

CREATE TABLE vendor_products
(
    vendor_id  BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    PRIMARY KEY (vendor_id, products_id),
    FOREIGN KEY (vendor_id) REFERENCES vendor (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (products_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE

);