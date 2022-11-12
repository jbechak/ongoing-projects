drop table if exists word_search;

begin transaction;

create table user (
    user_id serial,
    username varchar(50) not null,
    password_hash(300) not null

)

create table puzzle (
	puzzle_id serial,
	user_id integer references user,
	puzzle_type varchar(20),
	title varchar(60) not null,
	description varchar(500),
	difficulty varchar(10) not null,
	word_count int not null,
	width int not null check (width < 80),
	height int not null check (height < 80),
	genre varchar(20),
	instructions varchar(500),
	creator varchar(50),
	file_path varchar(100) not null,

	constraint pk_puzzle_id primary key (puzzle_id),
	--constraint ch_difficulty check (difficulty <= 3)
);

commit;

select *
from word_search;

insert into word_search (title, description, difficulty,
			word_count, width, height, genre, instructions, creator, file_path)
values ('bad', 'bass', 2, null, 20, 20, 'good', 'god', 'yoyo', 'yinz')
returning puzzle_id;
