INSERT INTO
    system_user(email, full_name, password, role, username)
VALUES ('tokichiihere@gmail.com',
        'Распопов Павел Сергеевич',
        '$2a$10$IsNwKo6Jufgi/ffp.U0iOuomHfzS7uhBMek/rQRa4HgOESi/U9FOy',
        'ADMIN',
        'admin');

INSERT INTO brand(name) VALUES ('Lada');
INSERT INTO brand(name) VALUES ('BMW');
INSERT INTO brand(name) VALUES ('Opel');

INSERT INTO model(name, brand_id) VALUES ('X-ray', 1);
INSERT INTO model(name, brand_id) VALUES ('M5', 2);
INSERT INTO model(name, brand_id) VALUES ('Astra', 3);

INSERT INTO
    modification(engine_model, name, year_from, year_to, model_id)
VALUES ('1.6 L VAZ-21129 I4',
        'X-ray 1.6 L VAZ-21129 I4',
        2014,
        2022,
        1);
INSERT INTO
    modification(engine_model, name, year_from, year_to, model_id)
VALUES ('4.4 L S63 twin-turbo V8',
        'M5 Competition 4.4 L S63 twin-turbo V8',
        2016,
        2023,
        2);
INSERT INTO
    modification(engine_model, name, year_from, year_to, model_id)
VALUES ('1.6 L GM MGE Turbo I4',
        'Astra K 1.6 L GM MGE Turbo I4',
        2015,
        2015,
        3);

INSERT INTO car(brand_id, model_id, modification_id) VALUES (1, 1, 1);
INSERT INTO car(brand_id, model_id, modification_id) VALUES (2, 2, 2);
INSERT INTO car(brand_id, model_id, modification_id) VALUES (3, 3, 3);

INSERT INTO dimension(dimension_name) values('л');
INSERT INTO dimension(dimension_name) values('шт');