CREATE TABLE IF NOT EXISTS users (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        payment_number VARCHAR(255) NOT NULL,
        type VARCHAR(255) NOT NULL,
        balance NUMERIC(19, 2) NOT NULL,
        user_id BIGINT NOT NULL,

        CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id)
);