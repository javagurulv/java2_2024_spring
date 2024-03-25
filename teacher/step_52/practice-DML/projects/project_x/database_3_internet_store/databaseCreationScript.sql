
CREATE SCHEMA IF NOT EXISTS sql_practice_internet_store
DEFAULT CHARACTER SET utf8 ;
USE sql_practice_internet_store;

commit;


CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX index_unique_clients_full_name
ON clients(full_name);

commit;


CREATE TABLE client_addresses (
    id BIGINT AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    post_code VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE UNIQUE INDEX index_unique_client_addresses_client_id
ON client_addresses(client_id);

commit;


CREATE TABLE products (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    manufacturer_country VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    availability INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX index_unique_products_title
ON products(title);

commit;


CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    total_price DOUBLE NOT NULL,
    order_date DATE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    amount INT NOT NULL,
    item_price DOUBLE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

commit;