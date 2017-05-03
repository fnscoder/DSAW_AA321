CREATE DATABASE dsaw_aa321;

USE dsaw_aa321;

CREATE TABLE crediarista(
nroCartao integer NOT NULL PRIMARY KEY,
nome varchar(45) NOT NULL,
cpf varchar(11) NOT NULL,
senha varchar(45) NOT NULL,
limite decimal(6,2) NOT NULL,
acumulado decimal(6,2) NOT NULL
);

INSERT INTO crediarista VALUES ("12345678", "John Lennon", "12345678900", MD5('123456'), 1500, 300);

INSERT INTO crediarista VALUES ("87654321", "Paul McCartney", "11111111100", MD5('111111'), 2000, 700);

INSERT INTO crediarista VALUES ("12341234", "George Harrison", "22222222200", MD5('222222'), 900, 400);

INSERT INTO crediarista VALUES ("56785678", "Ringo Star", "33333333300", MD5('333333'), 750, 300);

INSERT INTO crediarista VALUES ("18273645", "Brian Epstein", "44444444400", MD5('444444'), 3000, 1500);
