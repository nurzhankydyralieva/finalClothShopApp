DROP TABLE IF EXISTS products_vendor;

CREATE TABLE products_vendor
(
    vendor_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (vendor_id, product_id),
    FOREIGN KEY (vendor_id) REFERENCES vendors (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE

);