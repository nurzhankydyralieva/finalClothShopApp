DROP TABLE IF EXISTS vendors_products;

CREATE TABLE vendors_products
(
    vendor_id  BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    PRIMARY KEY (vendor_id, products_id),
    FOREIGN KEY (vendor_id) REFERENCES vendors (id),
    FOREIGN KEY (products_id) REFERENCES products (id)
);