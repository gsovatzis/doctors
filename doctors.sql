CREATE DATABASE  IF NOT EXISTS `doctors` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `doctors`;


DROP TABLE IF EXISTS Cities;
CREATE TABLE Cities (
  city_id int(11) NOT NULL AUTO_INCREMENT,
  city_name Varchar(30) NOT NULL,
  PRIMARY KEY (City_id) 
);
  

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  address varchar(30) NOT NULL,
  landline varchar(35) NULL,
  mobile varchar(35) NOT NULL,
  fax varchar(30) NULL,
  email varchar(30) NOT NULL unique ,
  pass varchar(30) NOT NULL,
  city_id int(11) NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY(city_id) references Cities(city_id)
);

DROP TABLE IF EXISTS Doctors;
CREATE TABLE Doctors (
  doctor_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  PRIMARY KEY (doctor_id),
  FOREIGN KEY (user_id) references Users(user_id)
);  
  

DROP TABLE IF EXISTS Specialties;
CREATE TABLE Specialties (
  specialty_id int(11) NOT NULL AUTO_INCREMENT,
  specialty_name Varchar(20) NOT NULL,
  PRIMARY KEY(specialty_id)
);
DROP TABLE IF EXISTS Doctors_Specialties;

CREATE TABLE Doctors_Specialties (
   doctor_id int(11) NOT NULL,
   specialty_id int(11) NOT NULL,
   FOREIGN KEY(doctor_id) references Doctors(doctor_id),
   FOREIGN KEY(specialty_id) references Specialties(specialty_id)
);

DROP TABLE IF EXISTS Working_hours;


CREATE TABLE Working_hours (
	working_hours_id int(11) NOT NULL AUTO_INCREMENT,
	work_day DATE,
	from_hour VARCHAR(11),
	to_hour VARCHAR(11),
	doctor_id int(11) NOT NULL,
	PRIMARY KEY(working_hours_id),
	FOREIGN KEY(doctor_id) references Doctors(doctor_id)
);

DROP TABLE IF EXISTS Appointments;


CREATE TABLE Appointments (
	appointment_id INT(11) NOT NULL AUTO_INCREMENT,
	user_id int(11) NOT NULL,
	doctor_id int(11) NOT NULL,
	appointment_date_time DATETIME(6) NOT NULL,
	medical_examination VARCHAR(200) NOT NULL,
	user_comments VARCHAR(300),
	rating int(11),
	PRIMARY KEY(appointment_id),
	FOREIGN KEY(user_id) references Users(user_id),
	FOREIGN KEY(doctor_id) references Doctors(doctor_id)
);

/* INSERT A COUPLE OF SAMPLE CITIES -> we need this in order to create a user */
INSERT INTO Cities (city_name)
VALUES ('Αθήνα');

INSERT INTO Cities (city_name)
VALUES ('Θεσσαλονίκη');

INSERT INTO Cities (city_name)
VALUES ('Πάτρα');

INSERT INTO Cities (city_name)
VALUES ('Ιωάννινα');

INSERT INTO Cities (city_name)
VALUES ('Πειραιάς');

/* INSERT SOME SAMPLE SPECIALTIES */
INSERT INTO specialties (specialty_name) VALUES ('Καρδιολόγος');
INSERT INTO specialties (specialty_name) VALUES ('Παθολόγος');
INSERT INTO specialties (specialty_name) VALUES ('Ουρολόγος');
INSERT INTO specialties (specialty_name) VALUES ('Παιδίατρος');


/* INSERT A SAMPLE USER (CUSTOMER) */
INSERT INTO Users (address,city_id, email, first_name, last_name, mobile, pass)
VALUES ('Σπύρου Πάτση 59', 
		(SELECT city_id FROM Cities WHERE city_name='Αθήνα'),
		'gsovatzis@gmail.com',
		'ΓΙΩΡΓΟΣ',
		'ΣΟΒΑΤΖΗΣ',
		'6934887117',
		'1234');

/* INSERT SOME SAMPLE DOCTORS */
INSERT INTO Users (address,city_id, email, first_name, last_name, mobile, pass)
VALUES ('Ροδόπης 1', 
		(SELECT city_id FROM Cities WHERE city_name='Αθήνα'),
		'dpetrakhs@hotmail.com',
		'Δημήτρης',
		'Πετράκης',
		'6939904744',
		'1234');

INSERT INTO Users (address,city_id, email, first_name, last_name, mobile, pass)
VALUES ('Τσαμαδού 25', 
		(SELECT city_id FROM Cities WHERE city_name='Πειραιάς'),
		'vpanagopoulos@gmail.com',
		'Βασίλης',
		'Παναγόπουλος',
		'6940899086',
		'1234');

/* MAKE THE USERS AS DOCTORS BY RELATING RECORDS INTO DOCTORS TABLE */
INSERT INTO Doctors (user_id)
VALUES ((SELECT user_id FROM users WHERE email='dpetrakhs@hotmail.com'));

INSERT INTO Doctors (user_id)
VALUES ((SELECT user_id FROM users WHERE email='vpanagopoulos@gmail.com'));

INSERT INTO Doctors_Specialties (doctor_id, specialty_id)
VALUES (
		(SELECT doctor_id FROM doctors INNER JOIN users ON doctors.user_id=users.user_id WHERE users.email='dpetrakhs@hotmail.com'),
		(SELECT specialty_id FROM specialties WHERE specialty_name='Καρδιολόγος')
	);

INSERT INTO Doctors_Specialties (doctor_id, specialty_id)
VALUES (
		(SELECT doctor_id FROM doctors INNER JOIN users ON doctors.user_id=users.user_id WHERE users.email='vpanagopoulos@gmail.com'),
		(SELECT specialty_id FROM specialties WHERE specialty_name='Παθολόγος')
	);



		
