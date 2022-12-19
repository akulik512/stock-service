--liquibase formatted sql

--changeset id:1
CREATE TABLE stock
(
    stock_id              uuid    NOT NULL PRIMARY KEY,
    companyName           varchar NOT NULL,
    shareName             varchar NOT NULL,
    shareIsinCode         varchar NOT NULL,
    country               varchar NOT NULL,
    fieldEconomicActivity varchar NOT NULL,
    pricePerShare         numeric NOT NULL,
    volume                numeric NOT NULL,
    date                  varchar NOT NULL,
    employeeId numeric NOT NULL
)
