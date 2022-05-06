DROP TABLE IF EXISTS cars;
CREATE TABLE  cars
(id Long AUTO_INCREMENT PRIMARY KEY,

 brand varchar(255) not null,
 color varchar(255) not null,
 model varchar(255) not null,
 year INT not null );