drop database  if exists enduroSoftware;
create database enduroSoftware;
use enduroSoftware;

create table estados(

	id_estado integer auto_increment not null primary key,
    nombre_estado varchar(30) not null

);

create table tallas(

	id_talla integer auto_increment not null primary key,
    n_talla varchar (10) not null
    
);

create table roles(

	id_rol integer auto_increment not null primary key,
    descripcion_rol varchar(30)

);

create table tipo_movimiento(

	id_tipo_movimiento integer auto_increment not null primary key,
    descripcion_tipo_movimiento varchar(30) not null

);

create table usuarios(
	
    id_usuario integer auto_increment primary key,
    nombre_usuario varchar(40) not null,
    apellido_usuario varchar (40) not null,
    nick_usuario varchar(50) not null unique,
    contraseña_usuario varchar(60)not null,
    correo_usuario varchar(60) not null unique,
    id_rol integer not null,
    id_estado integer not null,
    fecha_usuario_creado DATETIME DEFAULT CURRENT_TIMESTAMP,
    foreign key (id_rol) references roles(id_rol),
    foreign key (id_estado) references estados(id_estado)
	
    
);

create table productos(

	id_producto integer auto_increment not null primary key,
    nombre_producto varchar(40) not null,
    imagen_producto varchar(80) not null,
    precio decimal(12,2) not null,
    id_estado integer not null,
    foreign key (id_estado) references estados(id_estado)

);


create table producto_talla(
	id_producto_talla integer auto_increment not null primary key,
    id_producto integer not null,
    id_talla integer not null,
    stock integer not null,
    id_estado integer not null,
    foreign key (id_producto) references productos(id_producto),
    foreign key (id_talla) references tallas(id_talla),
    foreign key(id_estado) references estados(id_estado)
);

create table movimientos(

	id_movimiento integer auto_increment not null primary key,
    id_usuario integer not null,
    id_producto integer not null,
    tipo_movimiento integer not null,
    cantidad integer not null,
    id_estado integer not null,
    fecha_movimiento DateTime default current_timestamp,
    
    foreign key(id_producto) references productos(id_producto),
    foreign key(id_usuario) references usuarios(id_usuario),
    foreign key (tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
    foreign key(id_estado) references estados(id_estado)
    
);

-- poblar base de datos

-- Insertar estados
INSERT INTO estados (nombre_estado) VALUES
('Activo'), ('Inactivo');

-- Insertar tallas
INSERT INTO tallas (n_talla) VALUES
('36'), ('37'), ('38'), ('39'), ('40'), ('41'), ('42');

-- Insertar roles
INSERT INTO roles (descripcion_rol) VALUES
('Administrador'), ('Empleado');

-- Insertar tipos de movimiento
INSERT INTO tipo_movimiento (descripcion_tipo_movimiento) VALUES
('Entrada'), ('Salida');

-- Insertar usuarios (empleados y administrador)
INSERT INTO usuarios (
    nombre_usuario, apellido_usuario, nick_usuario, contraseña_usuario, correo_usuario, id_rol, id_estado
) VALUES 
('Carlos', 'Méndez', 'carlosm', 'pass1234', 'carlos.mendez@zapatos.com', 1, 1),
('Ana', 'García', 'anag', 'pass5678', 'ana.garcia@zapatos.com', 2, 1),
('Luis', 'Ramírez', 'luisr', 'pass9101', 'luis.ramirez@zapatos.com', 2, 1);

-- Insertar productos (zapatos)
INSERT INTO productos (
    nombre_producto, imagen_producto, precio, id_estado
) VALUES
('Zapato Deportivo Azul', 'zapato_deportivo_azul.jpg', 120000.00, 1),
('Zapato Formal Negro', 'zapato_formal_negro.jpg', 150000.00, 1),
('Zapato Casual Café', 'zapato_casual_cafe.jpg', 100000.00, 1);

-- Insertar stock de productos por talla
INSERT INTO producto_talla (id_producto, id_talla, stock, id_estado) VALUES
(1, 1, 10, 1), -- Zapato Deportivo Azul talla 36
(1, 2, 15,1), -- talla 37
(2, 3, 5,1),  -- Zapato Formal Negro talla 38
(2, 4, 8,1),  -- talla 39
(3, 5, 20,1), -- Zapato Casual Café talla 40
(3, 6, 7,1);  -- talla 41

-- Insertar movimientos (ejemplos de entradas y salidas)
INSERT INTO movimientos (
    id_usuario, id_producto, tipo_movimiento, cantidad, id_estado
) VALUES
(1, 1, 1, 25, 1), -- Entrada 25 zapatos deportivos azules
(2, 1, 2, 5, 1),  -- Salida 5 zapatos deportivos azules
(3, 3, 1, 30, 1), -- Entrada 30 zapatos casuales café
(2, 2, 2, 3, 1);  -- Salida 3 zapatos formales negros
