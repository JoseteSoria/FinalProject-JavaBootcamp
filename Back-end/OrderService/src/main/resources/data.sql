insert into orders (id, date, total_price, currency) values
(1, '2020-07-15 08:31:25', '7.12', 'EUR'),
(2, '2020-07-17 10:12:47', '18.91', 'EUR');

insert into medicine_ordered (id, medicine_id, medicine_name, quantity, order_id) values
(1, 4, 'Paracetamol Kern', 3, 1),
(2, 9, 'Ibuprofeno', 1, 1),
(3, 6, 'Ventolin', 1, 2),
(4, 1, 'Nolotil', 1, 2);