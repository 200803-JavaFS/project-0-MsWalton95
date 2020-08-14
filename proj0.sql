

--------------CUSTOMER--------------

CREATE TABLE customer (
	customer_id SERIAL PRIMARY KEY,
	user_name VARCHAR(20),
	pass_word VARCHAR(20),
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE,
	phone_number BIGINT NOT NULL);

INSERT INTO customer (user_name, pass_word, first_name, last_name, email, phone_number) VALUES
('customer1', 'customer1', 'Phoenix', 'Wright', 'naruhodo@aceattorney.com', 5551231234),
('customer2', 'customer2', 'Miles', 'Edgeworth', 'mitsurugi@aceattorney.com', 5554561234),
('customer3', 'customer3', 'Maya', 'Fey', 'mediumspirit@aceattorney.com', 5557891234);

INSERT INTO customer (user_name, pass_word, first_name, last_name, email, phone_number) VALUES
('customer5','customer5','John','Doe','johndoe@gmail.com',123456789);

--------------ACCOUNT--------------
CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	account_name VARCHAR(20),
	balance NUMERIC(12,2),
	account_type VARCHAR(30),
	approved BOOLEAN,
	customer_fk INTEGER REFERENCES customer(customer_id)
)


UPDATE account SET balance= balance+300  WHERE customer_fk=4 AND account_name='vacation';COMMIT;

ROLLBACK;

SELECT * FROM account WHERE approved=false


INSERT INTO account(account_name, balance, account_type, approved, customer_fk) VALUES
('default', 100.00, 'Checkings', true, 1),
('default', 1000.00, 'Checkings', true, 2),
('fancy ascots', 100.00, 'Savings', true, 2),
('default', 5.00, 'Checkings', true, 3),
('burgers',0.00,'Savings', false, 3)

--------------EMPLOYEE--------------
CREATE TABLE employee(
	employee_id SERIAL PRIMARY KEY,
	user_name VARCHAR(20),
	pass_word VARCHAR(20)
)

INSERT INTO employee(user_name, pass_word) VALUES
('employee1', 'employee1'),
('employee2', 'employee2'),
('employee3', 'employee3');
