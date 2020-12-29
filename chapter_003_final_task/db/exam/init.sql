create table meeting (
    id serial primary key,
    name varchar(100)
);

create table users (
    id serial primary key,
    name varchar(100)
);

create table meeting_users (
    id serial primary key,
    meeting_id int references meeting(id),
    user_id int references users(id),
    status boolean,
    UNIQUE (meeting_id, user_id)
);

insert into meeting(name) values
('Встреча 1'), ('Встреча 2'), ('Встреча 3'), ('Встреча 4');

insert into users(name) values
('Сотрудник 1'), ('Сотрудник 2'), ('Сотрудник 3'), ('Сотрудник 4'), ('Сотрудник 5');

insert into meeting_users(meeting_id, user_id, status) values
(1, 1, false), (1, 2, true), (1, 3, true), (1, 4, false),
(2, 1, true), (2, 2, false), (2, 3, true), (2, 5, true),
(4, 1, false), (4, 2, true), (4, 3, false), (4, 4, true);