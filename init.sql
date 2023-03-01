CREATE TABLE "roles" (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(20)
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE "users" (
	"id" SERIAL PRIMARY KEY,
	"username" VARCHAR(50),
	"email" VARCHAR(50),
	"password" VARCHAR(120)
);

INSERT INTO "users" ("id", "email", "password", "username") VALUES (1, 'user@tester.com', '$2a$10$b6fKyfskaSHM9I29sjLAGubjLnQjLOS5jriJPcsWpkIHYCHkrnUFG', 'user');
INSERT INTO "users" ("id", "email", "password", "username") VALUES (2, 'moderator@tester.com', '$2a$10$VdudRCzwK37eHf2zfC/V3ebC5GPG0WUELTrxAW46A5ggWAX0YKNzO', 'moderator');
INSERT INTO "users" ("id", "email", "password", "username") VALUES (3, 'admin@tester.com', '$2a$10$lt6Lt19ZfI0lPYHPOt0PEeh0ClEsRTcwyotP4Rp9ckBo29pm.SNQO', 'admin');

CREATE TABLE "user_roles" (
	"id" SERIAL PRIMARY KEY,
	"user_id" INTEGER,
	"role_id" INTEGER
);

INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
INSERT INTO user_roles(user_id, role_id) VALUES(2,2);
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
