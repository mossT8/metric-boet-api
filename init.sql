
CREATE TABLE "roles" (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(20)
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
COMMIT;

CREATE TABLE "users" (
	"id" SERIAL PRIMARY KEY,
	"firstname" VARCHAR(50),
	"lastname" VARCHAR(50),
	"phone" VARCHAR(15),
	"username" VARCHAR(50),
	"email" VARCHAR(50),
	"password" VARCHAR(120)
);

INSERT INTO "users" ("id", "email", "first_name", "last_name", "password", "phone", "username") VALUES (1, 'blikkies@test.com', 'Blikkies', 'Blignaut', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281161', 'Blikkies');
INSERT INTO "users" ("id", "email", "first_name", "last_name", "password", "phone", "username") VALUES (2, 'moderator@test.com', 'Michael', 'Rogers', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281162', 'Moderator');
INSERT INTO "users" ("id", "email", "first_name", "last_name", "password", "phone", "username") VALUES (3, 'admin@test.com', 'Jane', 'Doe', '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq', '0605281163', 'Admin');
COMMIT;

CREATE TABLE "user_roles" (
	"id" SERIAL PRIMARY KEY,
	"user_id" INTEGER,
	"role_id" INTEGER
);

INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
INSERT INTO user_roles(user_id, role_id) VALUES(2,2);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);

COMMIT;
