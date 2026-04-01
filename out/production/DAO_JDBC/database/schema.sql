CREATE DATABASE jdbc;

CREATE TABLE IF NOT EXISTS departments(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS sellers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(250),
    email VARCHAR(250) UNIQUE NOT NULL,
    birth_date TIMESTAMP,
    base_salary NUMERIC(7,2),
    department_id INTEGER,

    CONSTRAINT fk_department_id FOREIGN KEY(department_id) REFERENCES departments(id)
);

INSERT INTO departments(name)
VALUES('Comercial'), ('Tecnologia'), ('Financeiro'), ('Administrativo');

INSERT INTO sellers(name, email, birth_date, base_salary, department_id)
VALUES('Beatriz Souza', 'beatriz.souza@outlook.com', '1995-03-22 08:30:00', 4200.50, 3),
    ('Marcos Oliveira', 'marcos.oliveira@tech.com.br', '1988-11-10 14:15:00', 7800.00, 2),
    ('Fernanda Lima', 'fe.lima92@gmail.com', '1992-05-05 09:00:00', 3150.25, 1),
    ('Ricardo Santos', 'ricardo.santos_dev@yahoo.com', '2001-12-30 18:45:00', 5500.00, 4),
    ('Mariana Costa', 'mari.costa.vendas@hotmail.com', '1997-08-19 11:20:00', 2900.00, 2);