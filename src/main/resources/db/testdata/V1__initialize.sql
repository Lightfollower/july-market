create table users
(
    id bigserial,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table roles
(
    id         serial,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('DELETE_USERS_PERMISSION');

insert into users (username, password, email)
values ('Bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 3);

drop table if exists products cascade;
create table products
(
    id bigserial,
    title       varchar(255),
    description varchar(5000),
    price       int,
    primary key (id)
);
insert into products
    (title, description, price)
values ('Cheese', 'Fresh cheese', 320);

drop table if exists categories cascade;
create table categories
(
    id bigserial,
    title varchar(255),
    primary key (id)
);
insert into categories
    (title)
values ('Food'),
       ('Devices');

drop table if exists products_categories cascade;
create table products_categories
(
    product_id  bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    foreign key (product_id) references products (id),
    foreign key (category_id) references categories (id)
);
insert into products_categories (product_id, category_id)
values (1, 1);
drop table if exists orders cascade;
create table orders
(
    id bigserial,
    user_id      bigint        not null,
    price        numeric(8, 2) not null,
    address      varchar(255)  not null,
    phone_number varchar(30)   not null,
    primary key (id),
    constraint fk_user_id foreign key (user_id) references users (id)
);

drop table if exists orders_items cascade;
create table orders_items
(
    id bigserial,
    order_id   bigint not null,
    product_id bigint not null,
    quantity   int,
    price      numeric(8, 2),
    primary key (id),
    constraint fk_prod_id foreign key (product_id) references products (id),
    constraint fk_order_id foreign key (order_id) references orders (id)
);

