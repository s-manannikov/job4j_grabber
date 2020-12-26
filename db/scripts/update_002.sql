create table post(
                     id serial primary key,
                     header varchar(500),
                     description text,
                     link varchar(500) unique,
                     created timestamp
);