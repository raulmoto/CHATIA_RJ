drop database if exists chatia_rj;
create database chatia_rj default character set latin1;
use chatia_rj;
create table datos_entreno(
	id int auto_increment primary key not null,
    texto varchar (250),
    numeros int
)engine=InnoDB auto_increment=1;

create table preguntas(
	id int auto_increment primary key not null,
    pregunta varchar (250)
)engine=InnoDB auto_increment=1;

CREATE USER Jose IDENTIFIED BY 'jose_1234';
GRANT ALL privileges
ON chatia_rj.* TO Jose;

CREATE USER Raul IDENTIFIED BY 'Raul_1234';
GRANT ALL privileges
ON chatia_rj.* TO Raul;