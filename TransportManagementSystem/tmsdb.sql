Create database tmsdb;
USE tmsdb;
show tables;
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
select * from users;

INSERT INTO users (name, email, password) VALUES
('Ali Khan', 'ali@example.com', 'pass123'),
('Sana Mir', 'sana@example.com', 'mypassword'),
('Usman Tariq', 'usman@example.com', '123456');

CREATE TABLE bookings (
  booking_id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(100) NOT NULL,
  contact_info VARCHAR(100) NOT NULL,
  seats_booked INT NOT NULL,
  booking_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  vehicle_id INT DEFAULT NULL,
  status VARCHAR(20) DEFAULT NULL,
  payment_status VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (booking_id)
);

CREATE TABLE routes (
  route_id INT NOT NULL AUTO_INCREMENT,
  vehicle_id INT DEFAULT NULL,
  stop_name VARCHAR(100) DEFAULT NULL,
  stop_time TIME DEFAULT NULL,
  stop_order INT DEFAULT NULL,
  PRIMARY KEY (route_id),
  KEY (vehicle_id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

INSERT INTO routes (route_id, vehicle_id, stop_name, stop_time, stop_order) VALUES
(1,6,'Mandian','08:25:00',1),
(2,6,'Cantt Area','08:35:00',2),
(3,6,'Jhangi','08:45:00',3),
(4,6,'Kakul Road','08:55:00',4),
(5,6,'COMSATS Dhamtor','09:10:00',5),
(6,5,'Mandian','08:20:00',1),
(7,5,'Malikpura','08:30:00',2),
(8,5,'Fawara Chowk','08:40:00',3),
(9,5,'Kakul Road','08:50:00',4),
(10,5,'COMSATS Dhamtor','09:05:00',5),
(11,4,'Mandian','08:15:00',1),
(12,4,'Supply Bazaar','08:25:00',2),
(13,4,'Jhangi','08:35:00',3),
(14,4,'Kakul Road','08:45:00',4),
(15,4,'COMSATS Dhamtor','09:00:00',5),
(16,3,'Mandian','08:10:00',1),
(17,3,'Fawara Chowk','08:20:00',2),
(18,3,'Cantt Area','08:30:00',3),
(19,3,'Kakul Road','08:40:00',4),
(20,3,'COMSATS Dhamtor','08:55:00',5),
(21,2,'Mandian','08:05:00',1),
(22,2,'Malikpura','08:15:00',2),
(23,2,'Jhangi','08:25:00',3),
(24,2,'Kakul Road','08:35:00',4),
(25,2,'COMSATS Dhamtor','08:50:00',5),
(26,1,'Mandian','08:00:00',1),
(27,1,'Supply Bazaar','08:10:00',2),
(28,1,'Fawara Chowk','08:20:00',3),
(29,1,'Kakul Road','08:30:00',4),
(30,1,'COMSATS Dhamtor','08:45:00',5),
(31,12,'COMSATS Dhamtor','17:25:00',1),
(32,12,'Kakul Road','17:40:00',2),
(33,12,'Jhangi','17:50:00',3),
(34,12,'Cantt Area','18:00:00',4),
(35,12,'Mandian','18:10:00',5),
(36,11,'COMSATS Dhamtor','17:20:00',1),
(37,11,'Kakul Road','17:35:00',2),
(38,11,'Fawara Chowk','17:45:00',3),
(39,11,'Malikpura','17:55:00',4),
(40,11,'Mandian','18:05:00',5),
(41,10,'COMSATS Dhamtor','17:15:00',1),
(42,10,'Kakul Road','17:30:00',2),
(43,10,'Jhangi','17:40:00',3),
(44,10,'Supply Bazaar','17:50:00',4),
(45,10,'Mandian','18:00:00',5),
(46,9,'COMSATS Dhamtor','17:10:00',1),
(47,9,'Kakul Road','17:25:00',2),
(48,9,'Cantt Area','17:35:00',3),
(49,9,'Fawara Chowk','17:45:00',4),
(50,9,'Mandian','17:55:00',5),
(51,8,'COMSATS Dhamtor','17:05:00',1),
(52,8,'Kakul Road','17:20:00',2),
(53,8,'Jhangi','17:30:00',3),
(54,8,'Malikpura','17:40:00',4),
(55,8,'Mandian','17:50:00',5),
(56,7,'COMSATS Dhamtor','17:00:00',1),
(57,7,'Kakul Road','17:15:00',2),
(58,7,'Fawara Chowk','17:25:00',3),
(59,7,'Supply Bazaar','17:35:00',4),
(60,7,'Mandian','17:45:00',5);

CREATE TABLE vehicles (
  vehicle_id INT NOT NULL AUTO_INCREMENT,
  bus_number VARCHAR(20) DEFAULT NULL,
  driver_name VARCHAR(100) DEFAULT NULL,
  route_type ENUM('Morning','Evening') DEFAULT NULL,
  route VARCHAR(255) DEFAULT NULL,
  total_seats INT DEFAULT NULL,
  available_seats INT DEFAULT NULL,
  departure_time TIME DEFAULT NULL,
  PRIMARY KEY (vehicle_id)
);

INSERT INTO vehicles (vehicle_id, bus_number, driver_name, route_type, route, total_seats, available_seats, departure_time) VALUES
(1, 'MCU-101', 'Ali Khan', 'Morning', 'Main Campus to University', 40, 40, '08:00:00'),
(2, 'MCU-102', 'Zeeshan Ahmed', 'Morning', 'Main Campus to University', 40, 38, '08:00:00'),
(3, 'MCU-103', 'Imran Raza', 'Morning', 'Main Campus to University', 35, 35, '08:00:00'),
(4, 'MCU-104', 'Usman Javed', 'Morning', 'Main Campus to University', 45, 40, '08:00:00'),
(5, 'MCU-105', 'Farhan Saeed', 'Morning', 'Main Campus to University', 42, 42, '08:00:00'),
(6, 'MCU-106', 'Adeel Shah', 'Morning', 'Main Campus to University', 40, 36, '08:00:00'),
(7, 'UCM-201', 'Tariq Mehmood', 'Evening', 'University to Main Campus', 40, 39, '17:00:00'),
(8, 'UCM-202', 'Adnan Bashir', 'Evening', 'University to Main Campus', 35, 33, '17:00:00'),
(9, 'UCM-203', 'Sohail Akhtar', 'Evening', 'University to Main Campus', 45, 42, '17:00:00'),
(10, 'UCM-204', 'Junaid Khan', 'Evening', 'University to Main Campus', 40, 40, '17:00:00'),
(11, 'UCM-205', 'Ahmed Rauf', 'Evening', 'University to Main Campus', 40, 37, '17:00:00'),
(12, 'UCM-206', 'Bilal Haider', 'Evening', 'University to Main Campus', 42, 42, '17:00:00');

CREATE TABLE cancelled_bookings (
id INT AUTO_INCREMENT PRIMARY KEY,
customer_name VARCHAR(255) NOT NULL,
contact_info VARCHAR(255) NOT NULL,
vehicle_id INT NOT NULL,
seats_booked INT NOT NULL,
status VARCHAR(50),
payment_status VARCHAR(50),
cancellation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from bookings;
SELECT * FROM bookings WHERE payment_status = 'unpaid';
UPDATE bookings SET payment_status = 'unpaid' WHERE payment_status IS NULL;