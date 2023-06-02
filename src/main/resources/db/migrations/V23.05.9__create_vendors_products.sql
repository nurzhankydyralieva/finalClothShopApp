DROP TABLE IF EXISTS vendors_products;

CREATE TABLE vendors_products
(
    vendor_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (vendor_id, product_id),
    FOREIGN KEY (vendor_id) REFERENCES vendors (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);