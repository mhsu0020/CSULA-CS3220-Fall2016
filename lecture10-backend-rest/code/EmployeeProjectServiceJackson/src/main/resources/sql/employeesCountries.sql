drop table if exists project_members;
drop table if exists projects;
drop table if exists employees;
drop table if exists countries;


create table countries (
    id              integer auto_increment,
    name      		varchar(255),
    primary key(id)
);

insert into countries (name)
  values ('USA');
insert into countries (name)
  values ('Canada');
insert into countries (name)
  values ('China');


-- employees(id, first_name, last_name, address, supervisor_id)

create table employees (
    id              integer auto_increment,
    first_name      varchar(255),
    last_name       varchar(255),
    address         varchar(255),
    country_id 		integer references countries(id),
    primary key(id)
);

-- syntax for auto_increment, must include field names to skip providing id
insert into employees (first_name, last_name, address, country_id)
  values ('John', 'Doe', 'Street #215', 1);
insert into employees (first_name, last_name, address, country_id)
    values ('Michael', 'Hsu', 'Street #711', 1);
insert into employees (first_name, last_name, address, country_id)
    values ('Mark', 'Jacobs', 'somewhere in NYC', 1);
insert into employees (first_name, last_name, address, country_id)
    values ('Justin', 'Beiber', 'vancouver suburb', 2);
insert into employees (first_name, last_name, address, country_id)
    values ('Yao', 'Ming', 'Beijing', 3);
insert into employees (first_name, last_name, address, country_id)
    values ('Bill', 'Gates', 'Seattle', 1);
insert into employees (first_name, last_name, address, country_id)
    values ('Steve', 'Austin', 'WWE Headquarters', 1);

-- projects(id, name,leader_id )

create table projects (
    id          integer auto_increment,
    name        varchar(255),
    leader_id   integer references employees(id),
    primary key (id),
    -- uses id of employees as foreign key
    foreign key (leader_id) references employees(id)
);

insert into projects (name, leader_id)
  values ('Firestone', 2);
insert into projects (name, leader_id)
  values ('Blue', 1);
insert into projects (name, leader_id)
  values ('Windows OS', 6);

-- project_members( project_id, member_id )
create table project_members (
		project_id  integer references projects(id),
    member_id   integer references employees(id),
    member_team_rating integer,
    foreign key(project_id) references projects(id),
    foreign key(member_id) references employees(id)
);

insert into project_members values (1, 2, 8);
insert into project_members values (1, 4, 2);
insert into project_members values (2, 1, 10);
insert into project_members values (2, 3, 6);
insert into project_members values (2, 5, 7);
insert into project_members values (3, 6, 10);
insert into project_members values (3, 7, 9);
