
-- show leader info of project Blue
select p.name as 'project name', e.first_name as 'leader first name', e.last_name as 'leader last name' from projects p, employees e where p.leader_id = e.id and p.name = 'Blue';

-- count how many projects leader 1 has
select  count(id) as 'number of projects for leader 1' from projects where leader_id=1;

-- change employee 1 address to white house
update employees set address = 'white house' where id=1;
