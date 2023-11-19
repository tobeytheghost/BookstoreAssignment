--Populating the database with at least three sample books
INSERT INTO books (isbn, title, author, price, description) VALUES 
(2516, 'The Great Gatsby', 'F. Scott Fitzgerald', 12.99, 'The Great Gatsby is a classic novel by F. Scott Fitzgerald, set in the Roaring Twenties. It follows Jay Gatsbys extravagant pursuit of wealth and love, intertwined with the enigmatic Daisy Buchanan. The narrative explores themes of decadence, the American Dreams elusive nature, and the consequences of obsession. The story unfolds through the eyes of Nick Carraway, offering a critical portrayal of the Jazz Age and the pursuit of the American Dream.'),
(4683, 'The Handmaids Tale', 'Margaret Atwood', 12.99, 'a dystopian novel set in a theocratic society called Gilead. Offred, the protagonist, is a Handmaid, forced into reproductive servitude. The narrative explores oppression, loss of autonomy, and the impact of authoritarianism on women. Atwoods chilling tale reflects on gender, power, and the consequences of fundamentalism in a near-future world.'),
(8503, 'The Hunger Games', 'Suzanne Collins', 14.99, 'a dystopian novel set in Panem, a nation divided into districts. Katniss Everdeen volunteers for the deadly Hunger Games to save her sister. The annual event pits children against each other in a televised fight to the death. Katniss becomes a symbol of rebellion, challenging the Capitols tyranny. The narrative delves into themes of survival, sacrifice, and resistance, exploring the consequences of a society that exploits its citizens for entertainment and control.'),
(24125, 'Catching Fire', 'Suzanne Collins', 14.99, 'The second book in Suzanne Collins Hunger Games trilogy. After winning the Hunger Games, Katniss Everdeen faces new challenges as the Capitol tightens its grip. The Quarter Quell forces past victors, including Katniss and Peeta, back into the arena. '),
(54418, 'Mocking Jay', 'Suzanne Collins', 14.99, ' The third book in Suzanne Collins Hunger Games Triology. Set in the aftermath of rebellion, Katniss Everdeen becomes the symbol of the uprising against the Capitol. '),
(12469, 'IT', 'Stephen King', 19.99, 'Set in the town of Derry, Maine, the story alternates between two timelines. In the 1950s, a group of kids known as the Losers Club confronts an ancient evil entity, Pennywise the Dancing Clown, which resurfaces every 27 years. In the 1980s, the now-adult Losers return to Derry to face the entity once more. ');



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

