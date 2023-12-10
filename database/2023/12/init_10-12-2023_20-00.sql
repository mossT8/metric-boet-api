-- User table
CREATE TABLE User (
    Id INT PRIMARY KEY,
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- User table records
INSERT INTO User (Id, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, '2023-01-01', 0, '2023-01-01', 0), -- Admin user
    (2, '2023-02-01', 1', '2023-02-01', 1), -- User 1
    (3, '2023-03-01', 1, '2023-03-01', 1); -- User 2

-- User Parameter Type table
CREATE TABLE UserParameterType (
    Id INT PRIMARY KEY,
    ParameterName VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- User Parameter Type Records
INSERT INTO UserParameterType (Id, ParameterName, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 'FirstName', '2023-01-01', 1, '2023-01-01', 1),
    (2, 'Surname', '2023-01-01', 1, '2023-01-01', 1),
    (3, 'Username', '2023-01-01', 1, '2023-01-01', 1),
    (4, 'Email', '2023-01-01', 1, '2023-01-01', 1),
    (5, 'Password', '2023-01-01', 1, '2023-01-01', 1),
    (6, 'Salt', '2023-01-01', 1, '2023-01-01', 1),
    (7, 'CellPhone', '2023-01-01', 1, '2023-01-01', 1),;

-- User Parameters table
CREATE TABLE UserParameters (
    RecordId INT PRIMARY KEY,
    ParamTypeId INT,
    Value VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255),
    FOREIGN KEY (UserId) REFERENCES User(Id),
    FOREIGN KEY (ParamTypeId) REFERENCES UserParameterType(Id)
);

-- User Parameters for Admin
INSERT INTO UserParameters (RecordId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'Admin', '2023-01-01', 1, '2023-01-01', 1),
    (1, 2, 'User', '2023-01-01', 1, '2023-01-01', 1),
    (1, 3, 'admin', '2023-01-01', 1, '2023-01-01', 1),
    (1, 4, 'admin@tester.com', '2023-01-01', 1, '2023-01-01', 1),
    (1, 5, 'password123', '2023-01-01', 1, '2023-01-01', 1),
    (1, 6, 'todo', '2023-01-01', 1, '2023-01-01', 1),
    (1, 7, '0860030303', '2023-01-01', 1, '2023-01-01', 1),;

-- User Parameters for User 1
INSERT INTO UserParameters (RecordId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'User 1', '2023-01-01', 1, '2023-01-01', 1),
    (1, 2, 'User', '2023-01-01', 1, '2023-01-01', 1),
    (1, 3, 'user1', '2023-01-01', 1, '2023-01-01', 1),
    (1, 4, 'user@tester.com', '2023-01-01', 1, '2023-01-01', 1),
    (1, 5, 'password123', '2023-01-01', 1, '2023-01-01', 1),
    (1, 6, 'todo', '2023-01-01', 1, '2023-01-01', 1),
    (1, 7, '0860030303', '2023-01-01', 1, '2023-01-01', 1);

-- User Parameters for User 2
INSERT INTO UserParameters (RecordId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'User 2', '2023-01-01', 1, '2023-01-01', 1),
    (1, 2, 'User', '2023-01-01', 1, '2023-01-01', 1),
    (1, 3, 'user2', '2023-01-01', 1, '2023-01-01', 1),
    (1, 4, 'user@tester.com', '2023-01-01', 1, '2023-01-01', 1),
    (1, 5, 'password123', '2023-01-01', 1, '2023-01-01', 1),
    (1, 6, 'todo', '2023-01-01', 1, '2023-01-01', 1),
    (1, 7, '0860030303', '2023-01-01', 1, '2023-01-01', 1),;

-- Device table
CREATE TABLE Device (
    Id INT PRIMARY KEY,
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- Device table records
INSERT INTO Device (Id, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, '2023-01-01', 1, '2023-01-01', 1), -- Device 1
    (2, '2023-02-01', 1', '2023-02-01', 1), -- Device 2
    (3, '2023-03-01', 1, '2023-03-01', 1);  -- Device 3

-- Device Parameter Type table
CREATE TABLE DeviceParameterType (
    Id INT PRIMARY KEY,
    ParameterName VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- Device Parameter Type Records
INSERT INTO DeviceParameterType (Id, ParameterName, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 'Name', '2023-01-01', 1, '2023-01-01', 1),
    (2, 'Uid', '2023-01-01', 1, '2023-01-01', 1),
    (3, 'Bluetooth', '2023-01-01', 1, '2023-01-01', 1),
    (4, 'Humidity', '2023-01-01', 1, '2023-01-01', 1),
    (5, 'Temperature', '2023-01-01', 1, '2023-01-01', 1),
    (6, 'Light', '2023-01-01', 1, '2023-01-01', 1),
    (7, 'Percipitation', '2023-01-01', 1, '2023-01-01', 1),;

-- Device Parameters table
CREATE TABLE DeviceParameters (
    RecordId INT PRIMARY KEY,
    ParamTypeId INT,
    Value VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255),
    FOREIGN KEY (UserId) REFERENCES User(Id),
    FOREIGN KEY (ParamTypeId) REFERENCES UserParameterType(Id)
);


-- Device Parameter for Device 1
INSERT INTO DeviceParameters (RecordId, DeviceId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'Device 1', '2023-01-01', 1, '2023-01-01', 1),
    (1, 2, 'XYZ-0000001', '2023-01-02', 1, '2023-01-02', 1),
    (1, 3, 'live', '2023-01-03', 1, '2023-01-03', 1), -- Bluetooth
    (1, 4, '50%', '2023-01-04', 1, '2023-01-04', 1), -- Humidity
    (1, 5, '25°C', '2023-01-05', 1, '2023-01-05', 1), -- Temperature
    (1, 6, 'Low', '2023-01-06', 1, '2023-01-06', 1), -- Light
    (1, 7, '0 ml', '2023-01-07', 1, '2023-01-07', 1); -- Precipitation

-- Device Parameter for Device 2
INSERT INTO DeviceParameters (RecordId, DeviceId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (2, 1, 'Device 2', '2023-01-01', 1, '2023-01-01', 1),
    (2, 2, 'XYZ-0000002', '2023-01-02', 1, '2023-01-02', 1),
    (2, 3, 'live', '2023-01-03', 1, '2023-01-03', 1), -- Bluetooth
    (2, 4, '50%', '2023-01-04', 1, '2023-01-04', 1), -- Humidity
    (2, 5, '25°C', '2023-01-05', 1, '2023-01-05', 1), -- Temperature
    (2, 6, 'Low', '2023-01-06', 1, '2023-01-06', 1), -- Light
    (2, 7, '0 ml', '2023-01-07', 1, '2023-01-07', 1); -- Precipitation

-- Device Parameter for Device 3
INSERT INTO DeviceParameters (RecordId, DeviceId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (3, 1, 'Device 3', '2023-01-01', 1, '2023-01-01', 1),
    (3, 2, 'XYZ-0000002', '2023-01-02', 1, '2023-01-02', 1),
    (3, 3, 'live', '2023-01-03', 1, '2023-01-03', 1), -- Bluetooth
    (3, 4, '50%', '2023-01-04', 1, '2023-01-04', 1), -- Humidity
    (3, 5, '25°C', '2023-01-05', 1, '2023-01-05', 1), -- Temperature
    (3, 6, 'Low', '2023-01-06', 1, '2023-01-06', 1), -- Light
    (3, 7, '0 ml', '2023-01-07', 1, '2023-01-07', 1); -- Precipitation

-- Page table
CREATE TABLE Page (
    Id INT PRIMARY KEY,
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- Page Parameter Type table
CREATE TABLE PageParameterType (
    Id INT PRIMARY KEY,
    ParameterName VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255)
);

-- Inserting data into PageParameterType table
INSERT INTO PageParameterType (Id, ParameterName, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 'URL', '2023-01-01', 1, '2023-01-01', 1),
    (2, 'Html', '2023-01-02', 1, '2023-01-02', 1),
    (3, 'Visible for Moderators', '2023-01-03', 1, '2023-01-03', 1),
    (4, 'Visible for Users', '2023-01-04', 1, '2023-01-04', 1),
    (5, 'Visible on Navigation', '2023-01-05', 1, '2023-01-05', 1);

-- Page Parameters table
CREATE TABLE PageParameters (
    RecordId INT PRIMARY KEY,
    ParamTypeId INT,
    Value VARCHAR(255),
    CreatedDate DATETIME,
    CreatedUser VARCHAR(255),
    ModifiedDate DATETIME,
    ModifiedUser VARCHAR(255),
    FOREIGN KEY (UserId) REFERENCES User(Id),
    FOREIGN KEY (ParamTypeId) REFERENCES UserParameterType(Id)
);

-- Page Parameters for home page
INSERT INTO PageParameters (RecordId, PageId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'home', '2023-01-01', 1, '2023-01-01', 1), -- url
    (1, 2, '<p>This is the HTML content.</p>', '2023-01-02', 1, '2023-01-02', 1), -- html
    (1, 3, 'true', '2023-01-03', 1, '2023-01-03', 1), -- visibleForModerators
    (1, 4, 'true', '2023-01-04', 1, '2023-01-04', 1), -- visibleForUsers
    (1, 5, 'true', '2023-01-05', 1, '2023-01-05', 1); -- visibleOnNav

-- Page Parameters for about page
INSERT INTO PageParameters (RecordId, PageId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'about', '2023-01-01', 1, '2023-01-01', 1), -- url
    (1, 2, '<p>This is the HTML content.</p>', '2023-01-02', 1, '2023-01-02', 1), -- html
    (1, 3, 'true', '2023-01-03', 1, '2023-01-03', 1), -- visibleForModerators
    (1, 4, 'true', '2023-01-04', 1, '2023-01-04', 1), -- visibleForUsers
    (1, 5, 'true', '2023-01-05', 1, '2023-01-05', 1); -- visibleOnNav

-- Page Parameters for catelog page
INSERT INTO PageParameters (RecordId, PageId, ParamTypeId, Value, CreatedDate, CreatedUser, ModifiedDate, ModifiedUser)
VALUES
    (1, 1, 'catelog', '2023-01-01', 1, '2023-01-01', 1), -- url
    (1, 2, '<p>This is the HTML content.</p>', '2023-01-02', 1, '2023-01-02', 1), -- html
    (1, 3, 'true', '2023-01-03', 1, '2023-01-03', 1), -- visibleForModerators
    (1, 4, 'true', '2023-01-04', 1, '2023-01-04', 1), -- visibleForUsers
    (1, 5, 'true', '2023-01-05', 1, '2023-01-05', 1); -- visibleOnNav
