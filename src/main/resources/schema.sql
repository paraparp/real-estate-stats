CREATE TABLE real_estate_data
(
    date        DATE,
    location    VARCHAR(255),
    amount      INTEGER,
    total       INTEGER,
    percentil13 INTEGER,
    percentil33 INTEGER,
    price_m2    INTEGER,
    PRIMARY KEY (date, location)
);