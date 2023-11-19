--Populating the database with at least three sample books
INSERT INTO books (isbn, title, author, price, description) VALUES 
(2516, 'The Great Gatsby', 'F. Scott Fitzgerald', 12.99, 'DESCRIPTION'),
(4683, 'The Handmaids Tale', 'Margaret Atwood', 12.99, 'DESCRIPTION'),
(8503, 'The Hunger Games', 'Suzanne Collins', 14.99, 'DESCRIPTION');

--Populating Information for Admin
INSERT INTO sec_user (email, userName, encryptedPassword, enabled)
VALUES ('falzonm@sheridancollege.ca', 'admin', '$2a$12$l4zsT9wUr0qqyM1pGO6lK.abgRYMu9NwD5hYj64s.FYVc7KdvL6WS', 1);

INSERT INTO sec_user (email, userName, encryptedPassword, enabled)
VALUES ('test@test.com', 'test', '$2a$12$l4zsT9wUr0qqyM1pGO6lK.abgRYMu9NwD5hYj64s.FYVc7KdvL6WS', 1);

INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');
 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

