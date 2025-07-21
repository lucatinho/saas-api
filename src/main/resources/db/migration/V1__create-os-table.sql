CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS "user"
(
    id       UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enable   BOOLEAN      NOT NULL,
    role     VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS client
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      UUID,
    phone_number VARCHAR(100) NOT NULL,
    date         TIMESTAMP    NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS technical
(
    id      BIGSERIAL PRIMARY KEY,
    photo   VARCHAR(100) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS brand
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    description   VARCHAR(100) NOT NULL,
    stock         INTEGER      NOT NULL,
    minimum_stock INTEGER      NOT NULL,
    percent_sale  INTEGER      NOT NULL,
    brand_id      BIGSERIAL,
    category_id   BIGSERIAL,
    FOREIGN KEY (brand_id) REFERENCES brand (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
CREATE TABLE IF NOT EXISTS product_batch
(
    id             BIGSERIAL PRIMARY KEY,
    amount         INTEGER        NOT NULL,
    purchase_price DECIMAL(10, 2) NOT NULL,
    sale_price     DECIMAL(10, 2) NOT NULL,
    data_compra    TIMESTAMP      NOT NULL DEFAULT NOW(),
    product_id     BIGSERIAL      NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS status_os
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS service_os
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    product_id BIGSERIAL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS sales
(
    id           BIGSERIAL PRIMARY KEY,
    desconto     DECIMAL(10, 2) NOT NULL,
    valor_total  DECIMAL(10, 2) NOT NULL,
    sale_date    TIMESTAMP      NOT NULL DEFAULT NOW(),
    product_id   BIGSERIAL,
    FOREIGN KEY (product_id) REFERENCES product (id),
    client_id    BIGSERIAL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    technical_id BIGSERIAL,
    FOREIGN KEY (technical_id) REFERENCES technical (id)
);

CREATE TABLE IF NOT EXISTS sale_product_lot
(
    id               BIGSERIAL PRIMARY KEY,
    sale_id          BIGSERIAL      NOT NULL,
    product_batch_id BIGSERIAL,
    quantity         INTEGER        NOT NULL,
    unit_price       DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales (id),
    FOREIGN KEY (product_batch_id) REFERENCES product_batch (id)
);

CREATE TABLE IF NOT EXISTS os
(
    id             UUID                    DEFAULT gen_random_uuid() PRIMARY KEY,
    defeito        VARCHAR(100)   NOT NULL,
    laudo_tecnico  VARCHAR(100)   NOT NULL,
    status         VARCHAR(100)   NOT NULL,
    observacoes    VARCHAR(100)   NOT NULL,
    data_criacao   TIMESTAMP      NOT NULL DEFAULT NOW(),
    data_inicio    TIMESTAMP      NOT NULL,
    data_conclusao TIMESTAMP      NOT NULL,
    valor_total    DECIMAL(10, 2) NOT NULL,
    garantia_meses INTEGER        NOT NULL,
    client_id      BIGSERIAL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    technical_id   BIGSERIAL,
    FOREIGN KEY (technical_id) REFERENCES technical (id),
    service_os_id  BIGSERIAL,
    FOREIGN KEY (service_os_id) REFERENCES service_os (id),
    status_os_id   BIGSERIAL,
    FOREIGN KEY (status_os_id) REFERENCES service_os (id)
);