-- Database kaldt for carrental bliver skabt, hvis den ikke eksistere i forvejen.
DROP DATABASE IF EXISTS carrental;
CREATE DATABASE IF NOT EXISTS carrental;
USE carrental;

-- Tabel kaldt for cars bliver skabt, hvis den ikke eksistere i forvejen.
CREATE TABLE IF NOT EXISTS cars(
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    fuel VARCHAR(20),
    plate VARCHAR(30) UNIQUE,
    registration_year INT,
    registration_month INT,
    km INT,
    car_group VARCHAR(30) -- Jeg har valgt ikke at lave enum fordi det kan give bøvl hvis man skal tilføje en car group
);

-- Tabel kaldt for customer bliver skabt, hvis den ikke eksistere i forvejen.
CREATE TABLE IF NOT EXISTS customer(
    customer_id INT AUTO_INCREMENT PRIMARY KEY, -- PRIMARY KEY betyder at der ikke må være to ens værdier, og der kan kun være én pr tabel
    name VARCHAR(30),
    surname VARCHAR(30),
    adress VARCHAR(100),
    zip VARCHAR(10),
    city VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(50),
    driverLicenseNumber VARCHAR(30) UNIQUE, -- UNIQUE betyder at der ikke må være to ens værdier, men der må være flere unikke tal i én tabel
    driver_since DATE
);

-- Tabel kaldt for contracts bliver skabt, hvis den ikke eksistere i forvejen.
CREATE TABLE IF NOT EXISTS contracts(
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    car_id INT,
    from_dateTime DATETIME,
    to_dateTime DATETIME,
    max_km VARCHAR(30),
    current_km INT,

    FOREIGN KEY (customer_id) REFERENCES customer(customer_id), -- Foreign key er når vi referere til en value i en anden tabel
    FOREIGN KEY (car_id) REFERENCES cars(car_id)

);

-- Indsætter værdi
INSERT INTO cars (plate, brand, model, fuel, registration_year, registration_month, km, car_group)
VALUES
    ( 'DE93501', 'Audi', 'A5', 'Petrol', 2016, 7, 112000, 'Family'),
    ( 'ED09212', 'Volkswagen', 'Golf VI', 'Petrol', 2019, 3, 34000, 'Sport'),
    ( 'HF45231', 'Mercedes', 'C220d', 'Diesel', 2021, 2, 52000, 'Family'),
    ('DF50231', 'Hyundai', 'i20', 'Diesel', 2018, 12, 98000, 'Family');

INSERT INTO customer(name, surname, adress, zip, city, phone, email, driverLicenseNumber, driver_since)
VALUES
    ('Peter', 'Andersen', 'Møllegade 5', '2630', 'Høje Taastrup', '+4556302913', 'peter@gmail.com', '001324', '2000-01-24'),
    ('Mads', 'Mikkelsen', 'Trellevej 9', '2500', 'Køge', '+458801915', 'madsss@gmail.com', '55839', '1999-12-22'),
    ('Oskar', 'Olsen', 'Trippenvej 32', '2300', 'Glostrup', '+4582374821', 'olsenoskar@hotmail.com', '122132', '1977-11-02'),
    ('Mohammed', 'Ali', 'Vestergade 9', '2350', 'København K', '+4573647234', 'Moali@outlook.live', '983271', '1989-09-13');

INSERT INTO contracts(customer_id, car_id, from_dateTime, to_dateTime, max_km, current_km)
VALUES
    (1, 1, '2024-12-24 12:00:00', '2024-12-25 12:00:00', '100', 112000),
    (2, 2, '2024-09-22 12:00:00', '2024-09-23 12:00:00', '100', 193000),
    (3, 3, '2024-02-14 12:00:00', '2024-02-24 12:00:00', '500', 129000),
    (4, 4, '2024-01-24 12:00:00', '2024-02-04 12:00:00', '500', 113900);

-- Viser resultat
SELECT * FROM cars;
SELECT * FROM customer;
SELECT * FROM contracts;

