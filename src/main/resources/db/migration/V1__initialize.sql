drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price int, primary key(id));
insert into products
(title, description, price) values
('Cheese', 'Fresh cheese', 320),
('Milk', 'Fresh milk', 80),
('Apples', 'Fresh apples', 80),
('Bread', 'Fresh bread', 30),
('A1', '', 1),
('A2', '', 2),
('A3', '', 3),
('A4', '', 4),
('A5', '', 5),
('A6', '', 6),
('A7', '', 7),
('A8', '', 8),
('A9', '', 9),
('A10', '', 10),
('A11', '', 11),
('A12', '', 12),
('A13', '', 13),
('A14', '', 14),
('A15', '', 15),
('A16', '', 16),
('A17', '', 17),
('A18', '', 18),
('A19', '', 19),
('A20', '', 20);

drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('Food'),
('Devices');

drop table if exists products_categories cascade;
create table products_categories (product_id bigint not null, category_id bigint not null, primary key(product_id, category_id),
foreign key (product_id) references products(id), foreign key (category_id) references categories(id));
insert into products_categories (product_id, category_id) values (1, 1), (2, 1), (3, 1), (4, 2);

drop table if exists users;
create table users (
                       id                    bigserial,
                       phone                 VARCHAR(30) not null UNIQUE,
                       password              VARCHAR(80) not null,
                       email                 VARCHAR(50) UNIQUE,
                       first_name            VARCHAR(50),
                       last_name             VARCHAR(50),
                       PRIMARY KEY (id)
);

drop table if exists roles;
create table roles (
                       id                    serial,
                       name                  VARCHAR(50) not null,
                       primary key (id)
);

drop table if exists users_roles;
create table users_roles (
                             user_id               INT NOT NULL,
                             role_id               INT NOT NULL,
                             primary key (user_id, role_id),
                             FOREIGN KEY (user_id)
                                 REFERENCES users (id),
                             FOREIGN KEY (role_id)
                                 REFERENCES roles (id)
);

insert into roles (name)
values
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into users (phone, password, first_name, last_name, email)
values
('11111111','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Админ','Админов','admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(1, 3);