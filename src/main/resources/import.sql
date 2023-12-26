INSERT INTO roles (id, name) VALUES (1, "ADMIN");
INSERT INTO roles (id, name) VALUES (2, "USER");
INSERT INTO users (id, name, lastname, username, email, password) VALUES (1, "Angel", "Hincho", "ahincho", "ahincho@unsa.edu.pe", "$2a$10$1NZNQBOfL35xCtHn7l5k7uN82zoP8I6DObau06CnKTSmuPYA0uFtq");
INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);