create table user (
	id bigint primary key auto_increment,
	email varchar(255) unique not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	password varchar(255) not null,
	created_at timestamp not null,
	updated_at timestamp
);
