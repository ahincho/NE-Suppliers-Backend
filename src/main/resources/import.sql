-- THESE TABLES ARE NOT RELATED TO THE BUSINESS DOMAIN
-- Data for Roles Table
INSERT INTO roles (id, name) VALUES (1, "ADMIN");
INSERT INTO roles (id, name) VALUES (2, "USER");
-- Data for Users Table
INSERT INTO users (id, name, lastname, username, email, password) VALUES (1, "Angel", "Hincho", "ahincho", "ahincho@unsa.edu.pe", "$2a$10$1NZNQBOfL35xCtHn7l5k7uN82zoP8I6DObau06CnKTSmuPYA0uFtq");
-- Data for Users Roles Intermediate Table
INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);
-- TABLES RELATED TO THE BUSINESS DOMAIN
-- Data for States Table
INSERT INTO states (id, name) VALUES (1, "Active");
INSERT INTO states (id, name) VALUES (2, "Disabled");
INSERT INTO states (id, name) VALUES (3, "Deleted");
-- Data for Country Table
-- Data Recovered from: http://lanic.utexas.edu/subject/countries/indexesp.html
INSERT INTO countries (id, name, state_id) VALUES (1, "Peru", 1);
INSERT INTO countries (id, name, state_id) VALUES (2, "Chile", 1);
INSERT INTO countries (id, name, state_id) VALUES (3, "Argentina", 1);
INSERT INTO countries (id, name, state_id) VALUES (4, "Brazil", 1);
INSERT INTO countries (id, name, state_id) VALUES (5, "Ecuador", 1);
INSERT INTO countries (id, name, state_id) VALUES (6, "Bolivia", 1);
INSERT INTO countries (id, name, state_id) VALUES (7, "Colombia", 1);
INSERT INTO countries (id, name, state_id) VALUES (8, "Guatemala", 1);
INSERT INTO countries (id, name, state_id) VALUES (9, "Paraguay", 1);
INSERT INTO countries (id, name, state_id) VALUES (10, "Mexico", 1);
-- Data for Category Table
-- Data Recovered from: https://informi.co.uk/managing-people/finding-right-suppliers
-- Data Recovered from: https://theintactone.com/2020/01/19/categories-of-supplier/
INSERT INTO categories (id, name, state_id) VALUES (1, "Services", 1);
INSERT INTO categories (id, name, state_id) VALUES (2, "Contractors", 1);
INSERT INTO categories (id, name, state_id) VALUES (3, "Manufacturers", 1);
INSERT INTO categories (id, name, state_id) VALUES (4, "Producers", 1);
INSERT INTO categories (id, name, state_id) VALUES (5, "Distributors", 1);
INSERT INTO categories (id, name, state_id) VALUES (6, "Importers", 1);
INSERT INTO categories (id, name, state_id) VALUES (7, "Vendors", 1);
INSERT INTO categories (id, name, state_id) VALUES (8, "Wholesalers", 1);
-- Data Recovered from: https://www.filmsperu.pe/mejores-proveedores-retail-peru/
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (1, "New Trade Peru", "20519461545", 1, 1, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (2, "Larco Mar", "20265690361", 2, 2, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (3, "IRP Retail Institute", "20603564953", 3, 3, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (4, "Inverso", "20601645247", 4, 4, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (5, "Wolf Retail Solutions", "20495021336", 5, 5, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (6, "Ofimuebles Peru", "20480407483", 6, 6, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (7, "Lindley Arquitectos", "20545459591", 7, 7, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (8, "SSR Retail Security Systems", "20341137935", 8, 8, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (9, "Intercorp", "20602269109", 1, 9, 1);
INSERT INTO suppliers (id, name, ruc, category_id, country_id, state_id) VALUES (10, "Ripley Peru", "20337564373", 2, 10, 1);