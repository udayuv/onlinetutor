

CREATE TABLE User (
  uid int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  phone varchar(20) NOT NULL DEFAULT '',
    secret_question varchar(255),
    secret_answer varchar(255),
  password varchar(20) NOT NULL DEFAULT ''
);

ALTER TABLE User AUTO_INCREMENT=180;

insert into user (phone, password )values ('7080656583','u123'),('8015240286','v123');


CREATE TABLE Person_Info (
  pid int(11) unsigned NOT NULL PRIMARY KEY,
  first varchar(20)NOT NULL DEFAULT '',
  last varchar(20) NOT NULL DEFAULT '',
  gender varchar(5) DEFAULT '',
  email varchar(255) NOT NULL DEFAULT '',
  address varchar(255) DEFAULT ''
);

insert into person_info values (180,'uday','yadav', 'M','',''),(181,'rinu','singh', 'F','','');

CREATE TABLE Tutor (
  tutor_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  phone varchar(20) NOT NULL ,
  password varchar(20) NOT NULL ,
    secret_question varchar(255),
    secret_answer varchar(255),
  qual varchar(20) NOT NULL DEFAULT '',
  exp int(4) NOT NULL DEFAULT 0
);

ALTER TABLE Tutor AUTO_INCREMENT=100;

CREATE TABLE Course (
  course_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ctitle varchar(255) NOT NULL 
);


CREATE TABLE Approval (
  tutor_id int NOT NULL ,
  user_id int NOT NULL ,
  course_id int NOT NULL
  status varchar(50) NOT NULL  
);

CREATE TABLE Request (
  tutor_id int NOT NULL ,
  user_id int NOT NULL ,
  course_id int NOT NULL ,
  status varchar(50) NOT NULL
);

CREATE TABLE tutor_course (
  tutor_id int NOT NULL ,
  course_id int NOT NULL 
);

insert into tutor_course values
(100,1),
(101,2),
(100,3),
(101,3);

CREATE TABLE Feedback (
  tutor_id int NOT NULL ,
  user_id int NOT NULL ,
  course_id int NOT NULL ,
  feedback varchar(255) NOT NULL DEFAULT '',
  likes int 
);

insert into course (ctitle ) value('Java');
insert into course (ctitle ) value('JavaScript');
insert into course (ctitle ) value('MySql');
