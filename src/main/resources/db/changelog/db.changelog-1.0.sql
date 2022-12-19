--liquibase formatted sql

--changeset id:1
CREATE TABLE stock
(
    stock_id                uuid    NOT NULL PRIMARY KEY,
    company_name            varchar NOT NULL,
    share_name              varchar NOT NULL,
    share_isin_code         varchar NOT NULL,
    country                 varchar NOT NULL,
    field_economic_activity varchar NOT NULL,
    price_per_share         varchar NOT NULL,
    volume                  numeric NOT NULL,
    date                    varchar NOT NULL,
    employee_id             numeric NOT NULL
)
