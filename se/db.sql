CREATE DATABASE IF NOT EXISTS parttime_employee_management
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE parttime_employee_management;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS tblRegistrationShift;
DROP TABLE IF EXISTS tblShift;
DROP TABLE IF EXISTS tblEmployee;
DROP TABLE IF EXISTS tblRestaurant;
DROP TABLE IF EXISTS tblUser;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE tblUser (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(50) NOT NULL,
  description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblRestaurant (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  restaurantName VARCHAR(100) NOT NULL,
  address VARCHAR(255),
  description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE tblEmployee (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(20) NOT NULL UNIQUE,
  fullName VARCHAR(100) NOT NULL,
  phoneNumber VARCHAR(20),
  email VARCHAR(100),
  dob DATE,
  contractDate DATE,
  restaurantID INT NOT NULL,
  CONSTRAINT fk_employee_restaurant
    FOREIGN KEY (restaurantID) REFERENCES tblRestaurant(ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE tblShift (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  shiftNumber INT NOT NULL,
  description VARCHAR(255),
  startDate DATETIME NOT NULL,
  endDate DATETIME NOT NULL,
  UNIQUE KEY uk_shift_date_number (date, shiftNumber)
) ENGINE=InnoDB;

CREATE TABLE tblRegistrationShift (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  registeredTime DATETIME NOT NULL,
  status VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  employeeID INT NOT NULL,
  shiftID INT NOT NULL,
  userID INT NOT NULL,
  UNIQUE KEY uk_registration_employee_shift (employeeID, shiftID),
  CONSTRAINT fk_registration_employee
    FOREIGN KEY (employeeID) REFERENCES tblEmployee(ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT fk_registration_shift
    FOREIGN KEY (shiftID) REFERENCES tblShift(ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT fk_registration_user
    FOREIGN KEY (userID) REFERENCES tblUser(ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;

INSERT INTO tblUser (username, password, role, description) VALUES
('staff01', '123456', 'registration_staff', 'Registration staff account'),
('staff02', '123456', 'registration_staff', 'Registration staff account for testing');

INSERT INTO tblRestaurant (restaurantName, address, description) VALUES
('Parttime Restaurant - Branch 1', 'Ha Noi', 'Main restaurant branch'),
('Parttime Restaurant - Branch 2', 'Ho Chi Minh City', 'Second restaurant branch');

INSERT INTO tblEmployee (code, fullName, phoneNumber, email, dob, contractDate, restaurantID) VALUES
('E001', 'Nguyen Van An', '0901000001', 'an.nguyen@example.com', '2003-01-12', '2024-01-15', 1),
('E002', 'Tran Thi Binh', '0901000002', 'binh.tran@example.com', '2002-05-20', '2024-02-01', 1),
('E003', 'Le Minh Chau', '0901000003', 'chau.le@example.com', '2001-09-08', '2024-03-10', 1),
('E004', 'Pham Quoc Dung', '0901000004', 'dung.pham@example.com', '2003-11-25', '2024-04-05', 2),
('E005', 'Hoang Mai Linh', '0901000005', 'linh.hoang@example.com', '2002-07-30', '2024-05-18', 2);

SET @days_to_next_monday = (9 - DAYOFWEEK(CURDATE())) % 7;
SET @next_monday = DATE_ADD(CURDATE(), INTERVAL IF(@days_to_next_monday = 0, 7, @days_to_next_monday) DAY);

INSERT INTO tblShift (date, shiftNumber, description, startDate, endDate) VALUES
(@next_monday, 1, 'Shift 1: 08:00-16:00', TIMESTAMP(@next_monday, '08:00:00'), TIMESTAMP(@next_monday, '16:00:00')),
(@next_monday, 2, 'Shift 2: 16:00-24:00', TIMESTAMP(@next_monday, '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 1 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 1 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 1 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 1 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 1 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 1 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 2 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 2 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 2 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 2 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 2 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 2 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 3 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 3 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 3 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 3 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 3 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 3 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 4 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 4 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 4 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 4 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 4 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 4 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 5 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 5 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 5 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 5 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 5 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 5 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 6 DAY), '00:00:00')),
(DATE_ADD(@next_monday, INTERVAL 6 DAY), 1, 'Shift 1: 08:00-16:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 6 DAY), '08:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 6 DAY), '16:00:00')),
(DATE_ADD(@next_monday, INTERVAL 6 DAY), 2, 'Shift 2: 16:00-24:00', TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 6 DAY), '16:00:00'), TIMESTAMP(DATE_ADD(@next_monday, INTERVAL 7 DAY), '00:00:00'));

INSERT INTO tblRegistrationShift (registeredTime, status, description, employeeID, shiftID, userID) VALUES
(NOW(), 'registered', 'Sample existing registration', 1, 1, 1),
(NOW(), 'registered', 'Sample existing registration', 1, 2, 1);

SELECT 'parttime_employee_management database is ready' AS message;
SELECT COUNT(*) AS totalUsers FROM tblUser;
SELECT COUNT(*) AS totalEmployees FROM tblEmployee;
SELECT COUNT(*) AS totalNextWeekShifts FROM tblShift;
SELECT COUNT(*) AS totalRegistrations FROM tblRegistrationShift;
