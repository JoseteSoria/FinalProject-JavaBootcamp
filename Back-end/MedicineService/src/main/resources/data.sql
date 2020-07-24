insert into medicine (id, name, month_duration, min_price, min_currency, generic) values
(1, 'Nolotil', 24, 2.06, 'EUR', 0),
(2, 'Enantyum', 24, 4.00, 'EUR', 0),
(3, 'Adiro', 20, 1.45, 'EUR', 1),
(4, 'Paracetamol Kern', 24, 3.12, 'EUR', 0),
(5, 'Eutirox', 24, 4.09, 'EUR', 1),
(6, 'Ventolin', 32, 2.6, 'EUR', 0),
(7, 'Sintrom', 24, 2.33, 'EUR', 0),
(8, 'Paracetamol Cinfa', 24, 2.50, 'EUR', 1),
(9, 'Ibuprofeno', 24, 1.97, 'EUR', 0),
(10, 'Orfidal', 20, 2.47, 'EUR', 1),
(11, 'Omeprazol Alter', 24, 4.50, 'EUR', 0),
(12, 'Amoxicilina', 18, 2.90, 'EUR', 0),
(13, 'Almax', 24, 3.67, 'EUR', 0),
(14, 'Fluimil', 36, 2.86, 'EUR', 0),
(15, 'Espidifen', 24, 4.62, 'EUR', 1),
(16, 'Gelocatil', 26, 2.29, 'EUR', 0),
(17, 'Augmentine', 18, 3.40, 'EUR', 1),
(18, 'Omeprazol Teva', 24, 4.94, 'EUR', 0),
(19, 'Dalsy', 32, 5.43, 'EUR', 0),
(20, 'Couldina', 30, 6.15, 'EUR', 1);

insert into warehouse_medicine
(id, name, month_duration, min_price, min_currency, generic, price, currency, expiration_date)
values
(101, 'Ventolin', 32, 2.60, 'EUR', 0, 2.65, 'EUR', '2020-10-14'),
(102, 'Omeprazol Teva', 24, 4.94, 'EUR', 0, 5.00, 'EUR', '2021-02-10'),
(103, 'Ibuprofeno', 24, 1.97, 'EUR', 0, 2.02, 'EUR', '2021-06-21'),
(104, 'Ibuprofeno', 24, 1.97, 'EUR', 0, 2.02, 'EUR', '2021-06-21'),
(105, 'Amoxicilina', 18, 2.90, 'EUR', 0, 2.95, 'EUR', '2021-07-20'),
(106, 'Ventolin',32, 2.6, 'EUR', 0, 2.65, 'EUR', '2020-12-12'),
(107, 'Amoxicilina', 18, 2.90, 'EUR', 0, 2.95, 'EUR', '2021-06-08'),
(108, 'Ibuprofeno', 24, 1.97, 'EUR', 0, 2.02, 'EUR', '2021-07-21'),
(109, 'Amoxicilina', 18, 2.90, 'EUR', 0, 2.95, 'EUR', '2021-07-20'),
(110, 'Omeprazol Teva', 24, 4.94, 'EUR', 0, 5.00, 'EUR', '2021-02-10');