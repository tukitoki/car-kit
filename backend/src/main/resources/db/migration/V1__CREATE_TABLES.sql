CREATE TABLE system_user
(
    id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(80) NOT NULL,
    password VARCHAR(200) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    ROLE VARCHAR(50) NOT NULL
);
CREATE TABLE auto_repair_shop(
     id SERIAL NOT NULL PRIMARY KEY,
     name VARCHAR(200) NOT NULL,
     address VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE brand(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE model(
      id SERIAL NOT NULL PRIMARY KEY,
      name VARCHAR(100) NOT NULL UNIQUE,
      brand_id BIGINT NOT NULL REFERENCES brand (id)
);
CREATE TABLE modification(
      id SERIAL NOT NULL PRIMARY KEY,
      name VARCHAR(200) NOT NULL UNIQUE,
      engine_model VARCHAR(255) NOT NULL,
      year_from INT NOT NULL,
      year_to INT NOT NULL,
      model_id BIGINT NOT NULL REFERENCES model (id)
);
CREATE TABLE car(
    id SERIAL NOT NULL PRIMARY KEY,
    brand_id BIGINT NOT NULL REFERENCES brand (id),
    model_id BIGINT NOT NULL REFERENCES model (id),
    modification_id BIGINT NOT NULL REFERENCES modification (id)
);