INSERT INTO states (id, name) VALUES (1, "ACTIVE");
INSERT INTO states (id, name) VALUES (2, "INACTIVE");
INSERT INTO states (id, name) VALUES (3, "DELETED")
INSERT INTO roles (id, name) VALUES (1, "ADMIN");
INSERT INTO roles (id, name) VALUES (2, "USER");
INSERT INTO users (id, name, lastname, username, email, password) VALUES (1, "Angel", "Hincho", "ahincho", "ahincho@unsa.edu.pe", "$2a$10$1NZNQBOfL35xCtHn7l5k7uN82zoP8I6DObau06CnKTSmuPYA0uFtq");
INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);
-- Data Recovered from: https://www.filmsperu.pe/mejores-proveedores-retail-peru/
INSERT INTO suppliers (id, name, status) VALUES (1, "New Trade Peru", 1);
INSERT INTO suppliers (id, name, status) VALUES (2, "Larcomar", 1);
INSERT INTO suppliers (id, name, status) VALUES (3, "IRP Retail Institute", 1);
INSERT INTO suppliers (id, name, status) VALUES (4, "Inverso", 1);
INSERT INTO suppliers (id, name, status) VALUES (5, "Wolf Retail Solutions", 1);
INSERT INTO suppliers (id, name, status) VALUES (6, "Ofimuebles Peru", 1);
INSERT INTO suppliers (id, name, status) VALUES (7, "Lindley Arquitectos", 1);
INSERT INTO suppliers (id, name, status) VALUES (8, "SSR Retail Security Systems", 1);
INSERT INTO suppliers (id, name, status) VALUES (9, "Intercorp", 1);
INSERT INTO suppliers (id, name, status) VALUES (10, "Ripley Peru", 1);