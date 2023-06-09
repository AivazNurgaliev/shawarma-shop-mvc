DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS composition;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;


CREATE TABLE users
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    role         VARCHAR(255) NOT NULL,
    created_at   timestamptz  NOT NULL
);

CREATE TABLE categories
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE products
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    category_id INTEGER      NOT NULL,
    price       FLOAT        NOT NULL,
    description VARCHAR(255) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE orders
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id    INTEGER   NOT NULL,
    order_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE order_items
(
    order_id      INTEGER NOT NULL,
    product_id    INTEGER NOT NULL,
    product_count INTEGER NOT NULL,
    product_price FLOAT   NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (product_id) REFERENCES products (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE cart
(
    id             INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id     INTEGER NOT NULL,
    user_id        INTEGER NOT NULL,
    count_products INTEGER DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO users (first_name, last_name, email, phone_number, password, role, created_at)
VALUES ('Иван', 'Иванов', 'ivanov@mail.ru', '+7 (999) 123-45-67', '12345', 'ROLE_ADMIN', NOW()),
       ('Петр', 'Петров', 'petrov@mail.ru', '+7 (999) 765-43-21', '54321', 'ADMIN', NOW()),
       ('Анна', 'Сидорова', 'sidorova@mail.ru', '+7 (999) 111-22-33', '11111', 'admin', NOW());

INSERT INTO categories (name)
VALUES ('Классические шаурмы'),
       ('Шаурмы с курицей'),
       ('Шаурмы с говядиной'),
       ('Шаурмы с овощами');

INSERT INTO products (name, category_id, price, description)
VALUES ('Классическая шаурма', 1, 150, 'Ароматная шаурма с мясом и овощами'),
       ('Куриная шаурма', 2, 130, 'Нежное куриное мясо с овощами и соусом'),
       ('Говяжья шаурма', 3, 180, 'Сочное говяжье мясо с овощами и специями'),
       ('Овощная шаурма', 4, 100, 'Свежие овощи с соусом и специями');

INSERT INTO orders (user_id, order_date)
VALUES (2, NOW()),
       (3, NOW());

INSERT INTO order_items (order_id, product_id, product_count, product_price)
VALUES (1, 1, 2, 300),
       (1, 2, 1, 130),
       (2, 3, 3, 540),
       (2, 4, 2, 200);