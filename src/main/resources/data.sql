INSERT INTO AccessLevel (name)
VALUES ('admin'),
       ('moderator'),
       ('user');

INSERT INTO Account (login, password, email, access_level, active)
VALUES ('admin', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'admin@cos.com', 1, true),
       ('moderator', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'moderator@cos.com', 2, true),
       ('user1', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'user1@cos.com', 3, true),
       ('user2', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'user2@cos.com', 3, true);

INSERT INTO Ingredient (name, quantity, calories, proteins, carbohydrates, fats)
VALUES ('a', 1, 2, 3, 4, 5),
       ('b', 1, 2, 3, 4, 5),
       ('c', 1, 2, 3, 4, 5),
       ('d', 1, 2, 3, 4, 5),
       ('e', 1, 2, 3, 4, 5),
       ('f', 1, 2, 3, 4, 5);

INSERT INTO Recipe (name, author, description, rating, image, servings , calories, prepare_time_in_minutes, difficulty)
VALUES ('Carbonara', 3, 'pychotka', 5, null, 4, 1000, 50, 2),
       ('Nalesniki', 4, 'pychotka', 5, null, 4, 400, 50, 1),
       ('Spaghetti', 4, 'pychotka', 5, null, 4, 800, 50, 2);

INSERT INTO Recipe_Account (recipe_id, account_id)
VALUES (1, 4),
       (2, 3),
       (3, 3);

INSERT INTO Recipe_Ingredient (recipe_id, ingredient_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
