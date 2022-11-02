
INSERT into users (email, name) VALUES ('vinicius_rosa98@outlook.com', 'Vinicius');
INSERT into users (email, name) VALUES ('maria_joao@gmail.com', 'Maria');

INSERT into vehicle_financing (months, type_financing, value_installment, vehicle_price, user_id) VALUES (36, 'internal', 800, 20000, 1);
INSERT into vehicle_financing (months, type_financing, value_installment, vehicle_price, user_id) VALUES (60, 'external', 800, 20000, 2);

--Inner Join
--select vehicle_financing.months, vehicle_financing.type_financing, vehicle_financing.value_installment, vehicle_financing.vehicle_price, users.name, users.email FROM users INNER JOIN vehicle_financing ON users.id = vehicle_financing.user_id;
