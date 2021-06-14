INSERT INTO Access_Level (name)
VALUES ('admin'),
       ('moderator'),
       ('user');

INSERT INTO Account (login, password, email, access_level, active)
VALUES ('admin', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'admin@cos.com', 1, true),
       ('moderator', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'moderator@cos.com', 2, true),
       ('user1', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'ferrytales.info@gmail.com', 3, true),
       ('user2', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'user2@cos.com', 3, true),
       ('user3', 'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86', 'user3cos.com', 3, true);

INSERT INTO Ingredient (name, quantity, calories, proteins, carbohydrates, fats)
VALUES ('flour', 500, 2, 3, 4, 5),
       ('water', 200, 2, 3, 4, 5),
       ('sugar', 220, 2, 3, 4, 5),
       ('salt', 2, 2, 3, 4, 5),
       ('yeast', 50, 2, 3, 4, 5),
       ('magical ingredient', 10000, 2, 3, 4, 5);

INSERT INTO Recipe (name, author, description, rating, ratings_count, tags, image, servings , calories, prepare_time_in_minutes, difficulty)
VALUES ('Spaghetti carbonara', 'user1', 'pychotka', 5, 2, 'meat,noodle,pasta,savoury,gluten', null, 4, 1000, 50, 2),
       ('Spaghetti bolognese', 'user2', 'pychotka', 5, 13, 'meat,noodle,pasta,savoury,gluten', null, 4, 800, 50, 2),
       ('Crepes', 'user1', 'pychotka', 5, 4, 'vegan,sweet,gluten,dairy', null, 4, 400, 50, 1),
       ('Savoury crepes', 'user2', 'pychotka', 5, 13, 'meat,savoury,spicy,gluten,dairy', null, 4, 800, 50, 2),
       ('Spaghetti aglio olio', 'user2', 'pychotka', 5, 13, 'vegan,noodle,pasta,savoury,gluten', null, 4, 800, 50, 2),
       ('Lasagna', 'user2', 'pychotka', 5, 13, 'meat,noodle,pasta,savoury,gluten,dairy', null, 4, 800, 50, 2),
       ('Cherry soup', 'user2', 'pychotka', 5, 13, 'vegan,soup,sweet,fruit', null, 4, 800, 50, 2),
       ('Pierogi with blueberries', 'user2', 'pychotka', 5, 13, 'sweet,fruit,gluten,dairy', null, 4, 800, 50, 2),
       ('Tortilla', 'user2', 'pychotka', 5, 13, 'meat,noodle,gluten-free', null, 4, 800, 50, 2),
       ('Tikka masala', 'user2', 'mniam mniam mmmm', 5, 13, 'meat,rice,gluten-free,savoury,spicy', null, 4, 800, 50, 2),
       ('Paella', 'user2', 'pychotka', 5, 13, 'rice,seafood,savoury', null, 4, 800, 50, 2),
       ('Chili con carne', 'user2', 'mm ostro', 5, 13, 'meat,rice,savoury,spicy,gluten-free', null, 4, 800, 50, 2),
       ('Curry', 'user2', 'pychotka', 5, 13, 'vegan,rice,savoury,spicy,dairy', null, 4, 800, 50, 2),
       ('Pumpkin pie', 'user2', 'Najlepsze jakie kiedykolwiek jadłeś!', 5, 13, 'vegan,sweet,dessert,gluten', null, 4, 800, 50, 2),
       ('Cheesecake', 'user2', 'Żadna Grażka nie odmówi', 5, 13, 'sweet,dessert,gluten,dairy', null, 4, 800, 50, 2);

INSERT INTO Recipe_Account (recipe_id, account_id)
VALUES (3, 3),
       (5, 3),
       (7, 3),
       (1, 4),
       (2, 4),
       (5, 4),
       (6, 4),
       (9, 5),
       (10,5);


INSERT INTO Recipe_Ingredient (recipe_id, ingredient_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 5),
       (2, 6),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 5);

