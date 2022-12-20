create table MOVIEUSERS_220011
(
sl_no serial NOT NULL,
email_id character(100) not null,
user_name character(255) not null,
contactno character(20) not null,
password character(255) not null,
address character(255) not null,
primary key(email_id)
);

create table MOVIETHEATRE_220011
(
theatre_id character(100) NOT NULL,
theatre_name character(255) NOT NULL,
theatre_address character(255) NOT NULL,
maximum_seats int NOT NULL,
theatre_screens character(255) NOT NULL,
show_timings TIME NOT NULL,
primary key(theatre_id)
);

create table MOVIE_220011
(
theatre_id character(100) NOT NULL,
movie_id character(100) NOT NULL,
movie_name character(255) NOT NULL,
movie_language character(255) NOT NULL,
movie_standard character(100) NOT NULL,
movie_genre character(100) NOT NULL,
movie_start_date DATE NOT NULL,
movie_end_date DATE NOT NULL,
ticket_price int NOT NULL,
run_time TIME NOT NULL,
primary key(theatre_id, movie_id)
);

create table TRANSACTIonS_220011
(
theatre_id character(100) NOT NULL,
movie_id character(100) NOT NULL,
show_time TIME NOT NULL,
date_of_reservation DATE NOT NULL,
no_of_seatsbooked int NOT NULL,
no_of_availableseats int NOT NULL,
created_date TIMESTAMP NOT NULL,
updated_date TIMESTAMP NOT NULL,
primary key(theatre_id, movie_id, show_time, date_of_reservation)
);

DROP TABLE TRANSACTIonS_220011;

create table RESERVATIonS_220011
(
reservation_id serial NOT NULL,
email_id character(255) NOT NULL,
movie_id character(100) NOT NULL,
theatre_id character(100) NOT NULL,
date_of_reservation DATE NOT NULL,
show_time TIME NOT NULL,
time_of_reservation TIME NOT NULL,
no_of_seatsbooked int NOT NULL,
total_price int NOT NULL,
primary key(reservation_id)
);
DROP TABLE RESERVATIonS_220011;

select *
from MOVIETHEATRE_220011;

select *
from MOVIE_220011;

select M.MOVIE_NAME, T.THEATRE_NAME
from MOVIE_220011 AS M
inner join MOVIETHEATRE_220011 AS T
on M.THEATRE_ID = T.THEATRE_ID;

select THEATRE_ID, MAXIMUM_SEATS
from MOVIETHEATRE_220011;

select M.MOVIE_NAME, T.THEATRE_ID, M.MOVIE_ID 
from MOVIE_220011 AS M
inner join MOVIETHEATRE_220011 AS T
on M.THEATRE_ID = T.THEATRE_ID
where T.THEATRE_ID = '11';

select T.THEATRE_NAME, T.THEATRE_ID, M.MOVIE_ID
from MOVIETHEATRE_220011 AS T
inner join MOVIE_220011 AS M
on T.THEATRE_ID = M.THEATRE_ID
where M.MOVIE_ID = '101';

select MOVIE_START_DATE, MOVIE_END_DATE, THEATRE_ID
from MOVIE_220011
where MOVIE_ID = '101';

select M.MOVIE_START_DATE, M.MOVIE_END_DATE, T.THEATRE_ID
from MOVIETHEATRE_220011 AS T
inner join MOVIE_220011 AS M
on T.THEATRE_ID = M.THEATRE_ID
where T.THEATRE_ID = '10';

select TICKET_PRICE
from MOVIE_220011
where THEATRE_ID = '11' AND MOVIE_ID = '101';

create table MOVIETIMINGS_220011
(
theatre_id character(100) NOT NULL,
movie_id character(100) NOT NULL,
show_timings TIME NOT NULL,
primary key(theatre_id, movie_id, show_timings)
);
DROP TABLE MOVIETIMINGS_220011;

ALTER TABLE MOVIETHEATRE_220011 DROP show_timings;

select show_timings
from MOVIETIMINGS_220011
where MOVIE_ID = '101' AND THEATRE_ID = '10';


create table MOVIEIMAGES_220011
(
img_id character(100) NOT NULL,
imgfile character(255) NOT NULL,
primary key(img_id)
);
DROP TABLE MOVIEIMAGES_220011;

create table MOVIEDESCRIPTIon_220011
(
movieid character(100) NOT NULL,
moviename character(255) NOT NULL,
moviedirector character(255) NOT NULL,
movieproducer character(255) NOT NULL,
movieactor character(100) NOT NULL,
movieactress character(100) NOT NULL,
primary key(movieid)
);

select  email_id
from reservations_220011
where email_id = 'tr1@gmail.com';


select DISTINCT M.MOVIE_ID, M.MOVIE_NAME, I.imgfile
from MOVIE_220011 AS M
inner join MOVIEIMAGES_220011 AS I
on M.MOVIE_ID = I.IMG_ID
order by MOVIE_ID;

select THEATRE_ID, THEATRE_NAME
from MOVIETHEATRE_220011
where THEATRE_ID = '17';

ALTER TABLE MOVIE_220011
ADD COLUMN IMGFILE character(255);

select * from MOVIEDESCRIPTIon_220011 where MOVIENAME = 'Kantara';

