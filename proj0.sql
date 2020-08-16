
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

INSERT INTO customer (first_name, last_name, email, phone_number) VALUES
('Jane','Doe','janedoe@gmail.com',1234561234);

UPDATE customer SET user_name='customer7', pass_word='customer7' WHERE customer_id=7;

UPDATE customer SET email='janejane@gmail.com', phone_number=1002001234 WHERE customer_id=5;

ALTER TABLE customer ADD UNIQUE(user_name);

--------------ACCOUNT--------------
CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	account_name VARCHAR(20),
	balance NUMERIC(12,2),
	account_type VARCHAR(30),
	approved BOOLEAN,
	customer_fk INTEGER REFERENCES customer(customer_id)
)

INSERT account(account_name, balance, account_type, approved, customer_fk) INTO VALUES(?,?,?,?,?)

--SELECT customer_fk, SUM(balance) FROM account GROUP BY customer_fk ORDER BY customer_fk; --Show the amount for everything

-- SELECT LOCALTIMESTAMP -- Add transaction
-- 
SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1;

UPDATE account SET approved=true WHERE account_name='movie night' AND approved=false AND customer_fk=4;

DELETE FROM account WHERE account_name='vacation' AND approved=false AND customer_fk=4;

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
