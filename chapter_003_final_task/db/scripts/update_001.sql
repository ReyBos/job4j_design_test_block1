create table company (
    id serial primary key,
    name varchar(50)
);

create table person (
    id serial primary key,
    name varchar(50),
    company_id int references company(id)
);