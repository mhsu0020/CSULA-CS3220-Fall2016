drop table if exists project_members;
drop table if exists projects;
drop table if exists employees;

-- employees(id, first_name, last_name, address, supervisor_id)

create table employees (
    id              integer auto_increment,
    first_name      varchar(255),
    last_name       varchar(255),
    address         varchar(255),
    primary key(id)
);

-- syntax for auto_increment, must include field names to skip providing id
insert into employees (first_name, last_name, address)
  values ('John', 'Doe', 'Street #215');
insert into employees (first_name, last_name, address)
    values ('Jane', 'Doe', 'Street #711');

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
  values ('Firestone', 1);
insert into projects (name, leader_id)
  values ('Blue', 2);

-- project_members( project_id, member_id )
create table project_members (
		project_id  integer references projects(id),
    member_id   integer references employees(id),
    foreign key(project_id) references projects(id),
    foreign key(member_id) references employees(id)
);

insert into project_members values (1, 1);
insert into project_members values (2, 1);
insert into project_members values (2, 2);
