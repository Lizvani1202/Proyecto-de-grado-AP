create table usuario(
user_id int not null primary key,
primerNombre varchar(255),
apellidos varchar(255),
celular varchar(50),
email varchar(255),
password varchar(255)
);

Create table persona(
id_persona int Not null auto_increment,
user_id int,
organizacion int not null,
estado int not null,
primary key(id_persona),
foreign key (user_id)references usuario(user_id)
);

create table incidente(
id_incidente int Not null auto_increment primary key,
id_persona int not null,
longitud double not null,
latitud double not null,
descripcion varchar(255),
imagen varchar(255),
tipo varchar(255),
grado_incidente varchar(255),
foreign key (id_persona) references persona(id_persona)
);

create table caracteristicas_auto(
id_auto int not null primary key auto_increment,
id_persona int not null,
placa varchar(255) not null,
clase varchar(255) not null,
marca varchar(255) not null,
color varchar(255) not null,
tipo varchar(255) not null,
modelo varchar(255) not null,
servicio varchar(255) not null,
password varchar(255) not null,
foreign key (id_persona) references persona(id_persona)
);

create table detector(
id_detector int not null primary key auto_increment,
id_auto int not null,
placa varchar(255) not null,
punto_partida varchar(255) not null,
punto_llegada varchar(255) not null,
tiempo_recorrido double not null,
foreign key (id_auto) references caracteristicas_auto(id_auto)
);

create table multa(
id_multa int not null primary key auto_increment,
id_detector int not null,
velocidad double not null,
monto double not null,
foreign key (id_detector) references detector(id_detector)
);

select *from usuario;
