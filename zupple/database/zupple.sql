begin transaction;

drop table if exists wordsearch_word;
drop table if exists crossword_wordclue;
drop table if exists wordsearch;
drop table if exists crossword;
drop table if exists sudoku;
drop table if exists account;

create table account (
    user_id serial primary key,
    username varchar(50) not null,
    password_hash varchar(300) not null
);

create table sudoku (
    sudoku_id serial primary key,
    user_id integer references account (user_id),
    title varchar(50),
    difficulty integer,
    instructions varchar(250),
    grid_path varchar(200) not null,
    html_path varchar(200)
);

create table crossword (
    crossword_id serial primary key,
    user_id integer references account (user_id),
    title varchar(60),
    description varchar(500),
    difficulty varchar(20),
    genre varchar(20),
    instructions varchar(500),
    width int not null check (width < 200),
    height int not null check (height < 200),
    grid_path varchar(200) not null,
    html_path varchar(200)
);

create table wordsearch (
	wordsearch_id serial primary key,
	user_id not null integer references account (user_id),
	title varchar(60),
	description varchar(500),
	difficulty varchar(20),
	genre varchar(20),
    instructions varchar(500),
	width int not null check (width < 100),
	height int not null check (height < 100),
	grid_path varchar(200) not null,
    html_path varchar(200)
);

create table crossword_wordclue (
    wordclue_id serial primary key,
    crossword_id integer references crossword (crossword_id),
    clue_direction varchar(8),
    clue_number integer,
    word varchar(30),
    clue varchar(200)
);

create table wordsearch_word (
    word_id serial primary key,
    wordsearch_id integer references wordsearch (wordsearch_id),
    word varchar(30)
);

insert into account (username, password_hash)
values ('Justin', 'password');

insert into account (username, password_hash)
values ('Melodi', 'password');

insert into account (username, password_hash)
values ('Canly', 'password');

insert into wordsearch (user_id, title, difficulty, width, height, )


commit;
