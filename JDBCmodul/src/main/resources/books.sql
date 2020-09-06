create table if not exists books (bookID integer primary key auto_increment, name varchar(30) not null, price double);
insert into books (name, price) values ('inferno', 45);
insert into books (name, price) values ('harry potter', 45.5 );
insert into books (name, price) values ('it', 25);
insert into books (name, price) values ('spartacus', 55);
insert into books (name, price) values ('green mile', 20.5);
insert into books (name, price) values ('solomon key', 5);