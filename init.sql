-- Drop all roles
DROP TABLE IF EXISTS "device";
DROP TABLE IF EXISTS "user_roles";
DROP TABLE IF EXISTS "roles";
DROP TABLE IF EXISTS "users";
-- Create roles table
CREATE TABLE "roles" (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(20)
);
-- Insert role entries
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
COMMIT;

-- Create users table
CREATE TABLE "users" (
    "id" SERIAL PRIMARY KEY,
    "account_code" VARCHAR(50) NOT NULL,
    "first_name" VARCHAR(50) NOT NULL,
    "last_name" VARCHAR(50) NOT NULL,
    "phone" VARCHAR(50) NOT NULL,
    "username" VARCHAR(20) NOT NULL,
    "email" VARCHAR(50) NOT NULL,
    "password" VARCHAR(120) NOT NULL,
    "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "unique_username" UNIQUE ("username"),
    CONSTRAINT "unique_email" UNIQUE ("email"),
    CONSTRAINT "unique_account_code" UNIQUE ("account_code")
);

INSERT INTO "users" ("id", "account_code", "email", "first_name", "last_name", "password", "phone", "username") 
VALUES (1, 'A1', 'blikkies@test.com', 'Blikkies', 'Blignaut', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281161', 'Blikkies');
INSERT INTO "users" ("id", "account_code", "email", "first_name", "last_name", "password", "phone", "username") 
VALUES (2, 'A2', 'moderator@test.com', 'Michael', 'Rogers', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281162', 'Moderator');
INSERT INTO "users" ("id", "account_code", "email", "first_name", "last_name", "password", "phone", "username") 
VALUES (3, 'A3', 'admin@test.com', 'Jane', 'Doe', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281163', 'Admin');
COMMIT;

-- Create user_roles table
CREATE TABLE "user_roles" (
    "user_id" BIGINT NOT NULL,
    "role_id" BIGINT NOT NULL,
    CONSTRAINT "pk_user_roles" PRIMARY KEY ("user_id", "role_id"),
    CONSTRAINT "fk_user_roles_users" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE,
    CONSTRAINT "fk_user_roles_roles" FOREIGN KEY ("role_id") REFERENCES "roles"("id") ON DELETE CASCADE
);

INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
INSERT INTO user_roles(user_id, role_id) VALUES(2,2);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
COMMIT;

-- Create device table
CREATE TABLE "device" (
  "id" SERIAL PRIMARY KEY,
  "uuid" VARCHAR(255) NOT NULL,
  "name" VARCHAR(255) NOT NULL,
  "type" VARCHAR(255) NOT NULL,
  "location" VARCHAR(255) NOT NULL,
  "status" BOOLEAN NOT NULL DEFAULT TRUE,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "user_id" INT NOT NULL,
  "token" VARCHAR(255) NOT NULL UNIQUE,
  FOREIGN KEY ("user_id") REFERENCES "users"("id")
);
-- Insert some devices
INSERT INTO "device" ("uuid", "name", "type", "location", "status", "user_id", "token")
VALUES ('123e4567-e89b-12d3-a456-426655440000', 'Device 1', 'Sensor', 'Living Room', TRUE, 1, 'abcde12345');

INSERT INTO "device" ("uuid", "name", "type", "location", "status", "user_id", "token")
VALUES ('223e4567-e89b-12d3-a456-426655440000', 'Device 2', 'Actuator', 'Kitchen', FALSE, 1, 'fghij67890');

INSERT INTO "device" ("uuid", "name", "type", "location", "status", "user_id", "token")
VALUES ('323e4567-e89b-12d3-a456-426655440000', 'Device 3', 'Sensor', 'Bedroom', TRUE, 1, 'klmno12345');

INSERT INTO "device" ("uuid", "name", "type", "location", "status", "user_id", "token")
VALUES ('423e4567-e89b-12d3-a456-426655440000', 'Device 4', 'Actuator', 'Bathroom', TRUE, 1, 'pqrst67890');
COMMIT;

