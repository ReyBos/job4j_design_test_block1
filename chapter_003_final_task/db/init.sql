create table company (
    id serial primary key,
    name varchar(50)
);

create table person (
    id serial primary key,
    name varchar(50),
    company_id int references company(id)
);

insert into company(id, name) VALUES
(1, 'Компания 1'), (2, 'Компания 2'), (3, 'Компания 3'), (4, 'Компания 4'), (5, 'Компания 5');

insert into person(name, company_id) VALUES
('Работник 1', 1), ('Работник 2', 2), ('Работник 3', 3), ('Работник 4', 4), ('Работник 5', 5),
('Работник 6', 1), ('Работник 7', 1);

--names of all persons that are NOT in the company with id = 5
select name
from person
where company_id != 5;

--company name for each person
select person.name as person, company.name as company
from person
left join company on person.company_id = company.id;

-- Select the name of the company with the maximum number of persons + number of persons in this company
select company.name as company, count(person.id) as persons
from company
left join person on company.id = person.company_id
group by company.name
order by persons desc
limit 1;