drop database if exists T5DJ;
create database if not exists T5DJ;

use T5DJ;

create table tb_type(
id_type int primary key auto_increment,
name_type varchar(80) not null
);

create table tb_pelicula(
id_Peli int primary key auto_increment,
name_Peli varchar(100) not null,
description_Peli text not null,
director_Peli varchar(70) not null,
actors_Peli text not null,
idType_Peli int not null,
foreign key(idType_Peli) references tb_type(id_type)
);


/*tb_type*/
insert into tb_type(name_type) values ("Accion");
insert into tb_type(name_type) values ("Ciencia ficción");
insert into tb_type(name_type) values ("Belico");
insert into tb_type(name_type) values ("Drama");
insert into tb_type(name_type) values ("Fantasia");
insert into tb_type(name_type) values ("Romance");
insert into tb_type(name_type) values ("Terror");

/*tb_pelicula*/
insert into tb_pelicula(name_Peli, description_Peli, director_Peli, actors_Peli, idType_Peli)
	values ('Hacksaw Ridge','La historia de Desmond T. Doss, quien, debido a que se lo prohibía su fe, combatió en la Segunda Guerra Mundial sin portar un arma. No disparó ni una bala, pero salvó a 75 personas de la muerte en la batalla de la isla de Okinawa. Gracias a su coraje, pasó de ser el objeto de las burlas de sus compañeros a reconocérsele su enorme valía con la entrega de la Medalla de Honor del Congreso, otorgada por primera vez a un objetor de conciencia.', 'Mel Gibson', 'Andrew Garfield, Sam Worthington, Luke Bracey, Hugo Weaving, Teresa Palmer, Rachel Griffiths, Vince Vaughn', 3),
			('Capitán América: Civil War', 'Después de que otro incidente internacional, en el que se ven envueltos los Vengadores, produzca daños colaterales, la presión política obliga a poner en marcha un sistema para depurar responsabilidades.', 'Anthony y Joe Russo', 'Chris Evans, Robert Downey Jr., Scarlett Johansson, Sebastian Stan, Anthony Mackie, Don Cheadle, Jeremy Renner, Chadwick Boseman, Paul Bettany, Elizabeth Olsen, Paul Rudd, Emily VanCamp, Sally Field, Andrew Garfield, Frank Grillo, Martin Freeman, William Hurt, Daniel Brühl', 1),
            ('The Ring', 'Una reportera debe resolver el misterio de una cinta que trae muerte a sus espectadores, antes de que sucumba a su poder.', 'Gore Verbinski', 'Naomi Watts, Martin Henderson, David Dorfman, Brian Cox, Daveigh Chase', 7);
            
            
select * from tb_pelicula;
select * from tb_type;
            