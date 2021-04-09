
INSERT INTO usuarios (nombre, apellido_paterno, apellido_materno, email, password) VALUES ('Edwin', 'Vargas', 'Alvarado', 'edwin@gmail.com', '1234567890');
INSERT INTO usuarios (nombre, apellido_paterno, apellido_materno, email, password) VALUES ('Michel', 'Vargas', 'Alvarado', 'michel@gmail.com', '1234567890');

INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 0', 'Tecnologia', 344.99, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 1', 'Tecnologia', 400.89, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 2', 'Tecnologia', 450.77, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 3', 'Tecnologia', 500.66, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 4', 'Tecnologia', 309.78, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 5', 'Tecnologia', 999.66, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 6', 'Tecnologia', 233.56, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 7', 'Tecnologia', 354.44, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 8', 'Tecnologia', 355.54, 'Prueba la nueva tecnologia');
INSERT INTO productos (nombre, categoria, precio, descripcion) VALUES ('Producto 9', 'Tecnologia', 456.23, 'Prueba la nueva tecnologia');

INSERT INTO ventas(date, total, fk_user) VALUES (now(), 100, 1);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (1, 1, 100, 1, 100);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (1, 2, 200, 1, 200);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (1, 3, 300, 1, 300);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (1, 4, 400, 1, 400);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (1, 1, 500, 1, 500);


INSERT INTO ventas(date, total, fk_user) VALUES (now(), 200, 2);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (2, 6, 600, 1, 600);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (2, 7, 700, 1, 700);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (2, 8, 800, 1, 800);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (2, 9, 900, 1, 900);
INSERT INTO ventas_line(fk_venta, fk_producto, precio, cantidad, total) VALUES (2, 10, 1000, 1, 1000);
