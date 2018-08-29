create database partidos
use partidos

create table equipos(
idEquipo int not null primary key,
nombreEquipo varchar(50),
fechaCreacion date
)
select* from equipos
insert into equipos values(1,'Real Madrid', '2016')
insert into equipos values(2,'Sevilla', '2017')
insert into equipos values(3,'FC Barcelona', '2017')
insert into equipos values(4,'Atletico de Madrid', '2018')

create table jugadores(
idJugador int not null primary key,
idEquipo int not null references equipos(idEquipo),
nombre varchar(50),
apellido varchar(50)
)

insert into jugadores values (1,3,'Andres','Messi')
insert into jugadores values (2,3,'Luis','Suarez')
insert into jugadores values (3,2,'Aleix','Vidal')
insert into jugadores values (4,1,'Lucas','Modric')


create table arbitros(
idArbitro int not null primary key,
nombreArbitro varchar(50),
salario float
)

insert into arbitros values (1,'Howard',850.0)
insert into arbitros values (2,'Michael',900.0)
insert into arbitros values (3,'Luis',875.0)
insert into arbitros values (4,'Jordan',1150.0)

create table partidos(
idPartido int not null primary key,
idEquipoLocal int not null references equipos(idEquipo),
idEquipoVisita int not null references equipos(idEquipo),
idArbitro int not null references arbitros(idArbitro),
fecha date
)

insert into partidos values (1,1,2,1,'2018-08-05')
insert into partidos values (2,3,4,2,'2018-08-05')
insert into partidos values (3,4,1,3,'2018-08-15')
insert into partidos values (4,2,3,4,'2018-08-15')


select * from equipos
select * from jugadores
select * from arbitros
select * from partidos
select * from equipos where fechaCreacion>= '2017-01-01'
select * from jugadores where idEquipo=3
select * from arbitros where salario>= 800 and salario<=900
select * from partidos where idEquipoLocal= 1 OR idEquipoVisita=1
select idPartido,E.nombreEquipo,E2.nombreEquipo, idArbitro, fecha
from partidos , equipos as E, equipos as E2
where partidos.idEquipoLocal=E.idEquipo AND partidos.idEquipoVisita= E2.idEquipo