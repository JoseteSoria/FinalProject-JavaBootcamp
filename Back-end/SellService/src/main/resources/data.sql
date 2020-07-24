insert into sales (id, patient_id, user_id, total_price, currency, date) values
(1, 1, 2, 5.60, 'EUR', '2020-04-23 08:12:25'),
(2, 3, 2, 7.02, 'EUR', '2020-06-24 12:24:07'),
(3, 3, 1, 7.65, 'EUR', '2020-07-24 13:03:41');

insert into medicine_sold (id, medicine_id, medicine_name, price, currency, sales_id) values
(1, 94, 'Ventolin', '2.65', 'EUR', 1),
(2, 95, 'Amoxicilina', '2.95', 'EUR', 1),
(3, 96, 'Omeprazol Teva', '5.00', 'EUR', 2),
(4, 97, 'Ibuprofeno', '2.02', 'EUR', 2),
(5, 98, 'Omeprazol Teva', '5.00', 'EUR', 3),
(6, 99, 'Ventolin', '2.65', 'EUR', 3);