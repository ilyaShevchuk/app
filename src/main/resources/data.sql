INSERT INTO roles
VALUES(1, 'ROLE_ADMIN');

INSERT INTO roles
VALUES(2, 'ROLE_USER');

INSERT INTO users (user_id, fullname, username, password)
VALUES('101', 'Ilya Shevchuk', 'admin', 'iamadmin');

INSERT INTO users (user_id, fullname, username, password)
VALUES('102', 'Gosha Rubchinskiy', 'user', 'iamuser');

INSERT INTO user_role
VALUES('101', 1);

INSERT INTO user_role
VALUES('101', 2);

INSERT INTO user_role
VALUES('102', 2);

INSERT INTO available_place
VALUES (1, 1000, 1000, 1000, 1000, 1000, 50);

INSERT INTO available_place
VALUES (2, 3000, 500, 500, 500, 500, 50);

INSERT INTO available_place
VALUES (3, 1000, 1000, 1000, 1000, 500, 100);

INSERT INTO events
VALUES (1, '22.12.2021 20:00', 'Football match : Spartak Moskva - UFA', 1, 1);

INSERT INTO events
VALUES (2, '24.12.2021 18:00', 'Football match : Spartak Moskva - Zenit', 1, 2);

INSERT INTO events
VALUES (3, '25.12.2021 19:00', 'Football match : Spartak Moskva - Rostov', 1, 3);


