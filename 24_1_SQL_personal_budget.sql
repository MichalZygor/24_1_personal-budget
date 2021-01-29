CREATE SCHEMA `db_java_start` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

CREATE TABLE personal_budget (
	id INT PRIMARY KEY AUTO_INCREMENT,
    type ENUM('wydatek', 'przychód') NOT NULL,
    description VARCHAR(100) NOT NULL,
	amount DECIMAL(10,2) NOT NULL,
	date DATE NOT NULL
);

INSERT INTO personal_budget
	(type, description, amount, date)
VALUES
	('wydatek', 'Buty', 252.45, '2021-01-01'),
    ('wydatek', 'Lody', 3.50, '2021-01-05'),
    ('wydatek', 'Kebab', 56, '2021-01-11'),
    ('wydatek', 'Piwko', 9.95, '2021-01-10'),
    ('wydatek', 'Lidl', 185.60, '2021-01-21'),
    ('przychód', 'Pensja', 2100, '2021-02-07'),
    ('przychód', 'Kieszonkowe', 100, '2021-01-12'),
    ('przychód', 'Spadek', 2000, '2020-12-01');