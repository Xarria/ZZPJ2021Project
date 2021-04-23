DROP TABLE IF EXISTS Recipe;

CREATE TABLE Recipe
(
    id            bigint           NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name          varchar(100)     NOT NULL,
    author        bigint           NOT NULL,
    description   text             NOT NULL,
    rating        float4 DEFAULT 0 NOT NULL,
    tags          varchar(32)[]    NOT NULL,
    image         bytea[]          NOT NULL,
    servings      integer          NOT NULL,
    calories      integer,
    expected_time interval[]


);
