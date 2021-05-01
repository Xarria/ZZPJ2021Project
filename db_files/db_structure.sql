DROP TABLE IF EXISTS AccessLevel CASCADE;
DROP TABLE IF EXISTS Account CASCADE;
DROP TABLE IF EXISTS Ingredient CASCADE;
DROP TABLE IF EXISTS Recipe CASCADE;
DROP TABLE IF EXISTS Tag CASCADE;


CREATE TABLE AccessLevel
(
    id   bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name varchar(32),

    CONSTRAINT access_level_primary_key_constraint PRIMARY KEY (id)
);


CREATE TABLE Tag
(
    id   bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name varchar(32),

    CONSTRAINT tag_primary_key_constraint PRIMARY KEY (id)
);


CREATE TABLE Ingredient
(
    id            bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    quantity      bigint NOT NULL,
    calories      integer,
    proteins      integer,
    carbohydrates integer,
    fats          integer,

    CONSTRAINT ingredient_primary_key_constraint PRIMARY KEY (id)
);

CREATE TABLE Recipe
(
    id                      bigint           NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name                    varchar(100)     NOT NULL,
    author                  bigint           NOT NULL,
    description             text             NOT NULL,
    ingredients             bigint[],
    rating                  float4 DEFAULT 0 NOT NULL,
    tags                    varchar[]        NOT NULL,
    image                   bytea[]          NOT NULL,
    servings                integer          NOT NULL,
    calories                integer,
    prepare_time_in_minutes bigint           NOT NULL,
    difficulty              varchar,

    CONSTRAINT recipe_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT author_foreign_key_constraint FOREIGN KEY (author) REFERENCES Account (id),
    CONSTRAINT ingredients_foreign_key_constraint FOREIGN KEY (ingredients) REFERENCES Ingredient (id)
);

CREATE TABLE Account
(
    id               bigint       NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    login            varchar(24)  NOT NULL,
    password         varchar(64)  NOT NULL,
    email            varchar(100) NOT NULL,
    favorite_recipes bigint[],
    access_level     bigint       NOT NULL,
    active           boolean      NOT NULL,

    CONSTRAINT account_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT favorite_recipes_foreign_key_constraint FOREIGN KEY (favorite_recipes) REFERENCES Recipe (id),
    CONSTRAINT access_level_foreign_key_constraint FOREIGN KEY (access_level) REFERENCES AccessLevel (id)
);


CREATE TABLE RecipeTagCon
(
    id        bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    recipe_id bigint NOT NULL,
    tag_id    bigint NOT NULL,

    CONSTRAINT recipe_tag_con_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT recipe_id_foreign_key_constraint FOREIGN KEY (recipe_id) REFERENCES Recipe (id),
    CONSTRAINT tag_id_foreign_key_constraint FOREIGN KEY (tag_id) REFERENCES Tag (id)
);
