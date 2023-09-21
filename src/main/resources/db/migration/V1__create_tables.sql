use forodb;

create table IF NOT EXISTS roles (
    id bigint not null auto_increment,
    rol varchar(100),
    primary key (id)
);

create table IF NOT EXISTS usuarios (
    id bigint not null auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    nombre varchar(100) not null,
    estado varchar(100) not null,
    primary key (id)
);

create table IF NOT EXISTS usuarios_roles (
    id_usuario bigint not null,
    id_rol bigint not null,
    primary key (id_usuario, id_rol)
);

create table IF NOT EXISTS cursos (
    id bigint not null auto_increment,
    nombre varchar(100) not null unique,
    descripcion varchar(100) not null,
    estado varchar(100) not null,
    primary key (id)
);

create table IF NOT EXISTS topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descripcion varchar(100) not null,
    estatus varchar(100) not null,
    id_curso bigint not null,
    id_usuario bigint not null,
    fecha_creacion datetime not null,
    fecha_actualizacion datetime not null,
    primary key (id)
);

create table IF NOT EXISTS discusiones (
    id bigint not null auto_increment,
    mensaje varchar(300) not null,
    id_topico bigint not null,
    id_usuario bigint not null,
    fecha_creacion datetime not null,
    primary key (id)
);