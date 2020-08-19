CREATE OR REPLACE FUNCTION get_total() RETURNS NUMERIC
AS $$ 
SELECT SUM(balance) FROM account WHERE customer_fk=2;
$$ LANGUAGE SQL; 

SELECT get_total();

DROP FUNCTION get_total() CASCADE;

--------------CUSTOMER--------------
CREATE TABLE customer (
	user_name VARCHAR(20),
	pass_word VARCHAR(20),
	customer_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	phone_number BIGINT NOT NULL);

ALTER TABLE customer ALTER COLUMN email TYPE VARCHAR(50);

INSERT INTO customer (user_name, pass_word, first_name, last_name, email, phone_number) VALUES
('customer1', 'customer1','Phoenix', 'Wright', 'naruhodo@aceattorney.com', 5551231234),
('customer2', 'customer2','Miles', 'Edgeworth', 'mitsurugi@aceattorney.com', 5554561234),
('customer3', 'customer3','Maya', 'Fey', 'mediumspirit@aceattorney.com', 5557891234),
('customer4', 'customer4','Dick', 'Gumshoe', 'dickgumshoe@aceattorney.com', 5559119997),
('customer5', 'customer5','Emma', 'Skye', 'emmaskye@aceattorney.com', 5558859115);


INSERT INTO customer (first_name, last_name, email, phone_number) VALUES
('Larry', 'Butz', 'larrybutz@aceattorney.com', 5557728841),
('Cindy', 'Stone', 'cindystone@aceattorney.com', 5559587153),
('Mia', 'Fey', 'miafey@aceattorney.com', 5557539514),
('Will', 'Power', 'willpower@aceattorney.com', 5559873994),
('Jack', 'Hammer', 'jackhammer@aceattorney.com', 5559117429);


DROP TABLE customer CASCADE;

--------------ACCOUNT--------------
CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	account_name VARCHAR(20) NOT NULL,
	balance NUMERIC(10,2),
	account_type VARCHAR(30) NOT NULL,
	approved BOOLEAN,
	customer_fk INTEGER REFERENCES customer(customer_id)
)

INSERT INTO account(account_name, balance, account_type, approved, customer_fk) VALUES
('default', 250.00,'Checkings', TRUE, 1),
('default', 10.00, 'Savings', TRUE, 1),
('default', 5000.00,'Checkings', TRUE, 2),
('default', 100.00,'Savings', TRUE, 2),
('default', 50.00,'Checkings', TRUE, 3),
('default', 5.00, 'Savings', TRUE, 3),
('burgers', 0.00,'Savings', FALSE, 3),
('default', 75.00, 'Checkings', TRUE, 4),
('default', 0.00,'Savings', TRUE, 4),
('vacation', 0.00,'Savings', FALSE, 4),
('new coat', 0.00, 'Savings', FALSE, 4),
('default', 50.00,'Checkings', TRUE, 5),
('default', 10.00, 'Savings', TRUE, 5),
('default', -100.00,'Checkings', TRUE, 9),
('default', -5.00, 'Savings', TRUE, 9),
('art supplies', 0.00,'Savings', FALSE, 9),
('photography', 0.00,'Savings', FALSE, 9),
('default', 150.00,'Checkings', TRUE, 10),
('default', 50.00, 'Savings', TRUE, 10),
('default', 2500.00,'Checkings', TRUE, 11),
('default', 100.00,'Savings', TRUE, 11),
('default', 4000.00,'Checkings', TRUE, 12),
('default', 30.00,'Savinings', TRUE, 12),
('steel samurai', 100.00,'Savings', TRUE, 12),
('default', 4000.00,'Checkings', TRUE, 13),
('default', 100.00,'Savings', TRUE, 13),
('movies', 200.00, 'Savings', TRUE, 13),
('steak', 0.00,'Savings', FALSE, 13);

DROP TABLE account CASCADE;

--------------TRANSACTION--------------
CREATE TABLE transactions(
	transaction_id SERIAL PRIMARY KEY,
	transaction_type VARCHAR(10),
	transaction_amount NUMERIC(10,2),
	total_balance NUMERIC(10,2),
	customer_fk INTEGER REFERENCES customer(customer_id),
	updated_time timestamp not null default now()
)

DROP TABLE transactions CASCADE;

--------------EMPLOYEE--------------
CREATE TABLE employee(
	employee_id SERIAL PRIMARY KEY,
	user_name VARCHAR(20) NOT NULL,
	pass_word VARCHAR(20) NOT NULL,
	administrator BOOLEAN
)

INSERT INTO employee(user_name, pass_word, administator) VALUES
('employee1', 'employee1', FALSE),
('employee2', 'employee2', FALSE),
('employee3', 'employee3', FALSE),
('employee4', 'employee4', TRUE),
('employee5', 'employee5', TRUE);

------------------------------------
