CREATE DATABASE IF NOT EXISTS tienda;
USE tienda;
CREATE TABLE IF NOT EXISTS cliente (
    idcliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    dni VARCHAR(9) NOT NULL,
    direccion  VARCHAR(30) NOT NULL,
    fechanato DATE
);
CREATE TABLE IF NOT EXISTS producto (
    idproducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    idproveedor INT
);
CREATE TABLE IF NOT EXISTS proveedor (
    idproveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    nif VARCHAR(9) NOT NULL,
    direccion VARCHAR(30) NOT NULL
);
create table producto_proveedor( idproducto_proveedor int auto_increment primary key, idproducto int not null,
idproveedor int not null,
precio decimal(10,4) not null
);
alter table producto add
FOREIGN KEY fk_proveedor (idproveedor) REFERENCES proveedor (idproveedor) ON DELETE restrict
ON UPDATE cascade;
alter table producto_proveedor add  
FOREIGN KEY fk_producto (idproducto) REFERENCES producto(idproducto) ON DELETE restrict
ON UPDATE cascade;
alter table producto_proveedor 
add FOREIGN KEY fk_proveedor (idproveedor) REFERENCES proveedor(idproveedor) ON DELETE restrict
ON UPDATE cascade;
insert into proveedor(nombre, nif, direccion) 
values 
("De fruta madre","B1231231","Lleida"),
("L'hort de l'avia","B716721","Girona"),
("De la mar el mero","B2334231","Tarragona");
insert into producto(nombre,idproveedor) 
values 
("Manzana",1),
("Pera",1),
("Brócoli",2),
("Salmonete",3);
insert into producto_proveedor(idproducto,idproveedor,precio) 
values 
(1,1,20),(1,2,19),(2,1,10),(3,2,10),(4,3,80);
select * from producto;
select * from proveedor;
select * from producto_proveedor;
select proveedor.nombre proveedor, producto.nombre producto, precio from proveedor join producto_proveedor on proveedor.idproveedor=producto_proveedor.idproveedor
join producto on producto_proveedor.idproducto=producto.idproducto;