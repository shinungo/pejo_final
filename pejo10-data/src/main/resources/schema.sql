

DROP TABLE IF EXISTS test;
CREATE TABLE test (ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(50), PASSWORD VARCHAR(255));
INSERT INTO test (ID, NAME, PASSWORD) values (1,'test','password-hash');


DROP TABLE IF EXISTS bankuser cascade; 
CREATE TABLE bankuser (
	id INT NOT NULL AUTO_INCREMENT, 
	name VARCHAR(50),
	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS iban; 
CREATE TABLE iban (
	id INT NOT NULL AUTO_INCREMENT, 
	ibannumber VARCHAR(50),
	user_id INT NOT NULL, 
	PRIMARY KEY (id),
	CONSTRAINT `bankuser_ibfk_1` 
	FOREIGN KEY (`user_id`) REFERENCES `bankuser` (`id`)
); 