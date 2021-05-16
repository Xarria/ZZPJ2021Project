DROP TABLE IF EXISTS Access_Level CASCADE;
DROP TABLE IF EXISTS Account CASCADE;
DROP TABLE IF EXISTS Ingredient CASCADE;
DROP TABLE IF EXISTS Recipe CASCADE;
DROP TABLE IF EXISTS Recipe_Account CASCADE;
DROP TABLE IF EXISTS Recipe_Ingredient CASCADE;

CREATE TABLE Access_Level
(
    id   bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name varchar(32),

    CONSTRAINT access_level_primary_key_constraint PRIMARY KEY (id)
);

CREATE TABLE Account
(
    id           bigint       NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    login        varchar(24)  NOT NULL,
    password     varchar(128) NOT NULL,
    email        varchar(100) NOT NULL,
    access_level bigint       NOT NULL,
    active       boolean      NOT NULL,

    CONSTRAINT account_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT login_unique_constraint UNIQUE (login),
    CONSTRAINT email_unique_constraint UNIQUE (email),
    CONSTRAINT access_level_foreign_key_constraint FOREIGN KEY (access_level) REFERENCES Access_Level (id)
);

CREATE TABLE Ingredient
(
    id            bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name          varchar(60),
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
    rating                  float4 DEFAULT 0 NOT NULL,
    image                   bytea[],
    servings                integer          NOT NULL,
    calories                integer,
    prepare_time_in_minutes bigint           NOT NULL,
    difficulty              varchar,

    CONSTRAINT recipe_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT author_foreign_key_constraint FOREIGN KEY (author) REFERENCES Account (id)
);

CREATE TABLE Recipe_Account
(
    id         bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    recipe_id  bigint NOT NULL,
    account_id bigint NOT NULL,

    CONSTRAINT recipe_account_con_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT recipe_id_foreign_key_constraint FOREIGN KEY (recipe_id) REFERENCES Recipe (id),
    CONSTRAINT account_id_foreign_key_constraint FOREIGN KEY (account_id) REFERENCES Account (id)
);

CREATE TABLE Recipe_Ingredient
(
    id            bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    recipe_id     bigint NOT NULL,
    ingredient_id bigint NOT NULL,

    CONSTRAINT recipe_ingredient_con_primary_key_constraint PRIMARY KEY (id),
    CONSTRAINT recipe_id_foreign_key_constraint FOREIGN KEY (recipe_id) REFERENCES Recipe (id),
    CONSTRAINT ingredient_id_foreign_key_constraint FOREIGN KEY (ingredient_id) REFERENCES Ingredient (id)
)
