-- Вставка данных в таблицу models
INSERT INTO models
VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', TRUE, 'Premium', 'Черный', NULL, NULL, 'Samsung', 1200.00, 'SN-TV-123', '55 дюймов', 'QLED', '11111111-1111-1111-1111-111111111111'),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', TRUE, 'Premium', 'Серый', NULL, NULL, 'Samsung', 1500.00, 'SN-TV-456', '65 дюймов', 'QLED', '11111111-1111-1111-1111-111111111111'),
    ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb', TRUE, 'Синий', NULL, 'Компактный', 5, 'Dyson', 700.00, 'SN-VAC-001', NULL, NULL, '22222222-2222-2222-2222-222222222221'),
    ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', TRUE, 'Красный', NULL, 'Компактный', 7, 'Dyson', 750.00, 'SN-VAC-002', NULL, NULL, '22222222-2222-2222-2222-222222222221'),
    ('ccccccc1-cccc-cccc-cccc-cccccccccccc', TRUE, 'Белый', 'Инверторный', 2, NULL, 'LG', 1600.00, 'SN-FR-001', '400 л', NULL, '33333333-3333-3333-3333-333333333331'),
    ('ccccccc2-cccc-cccc-cccc-cccccccccccc', TRUE, 'Серый', 'Линейный', 3, NULL, 'LG', 1800.00, 'SN-FR-002', '450 л', NULL, '33333333-3333-3333-3333-333333333331'),
    ('ddddddd1-dddd-dddd-dddd-dddddddddddd', TRUE, 'Графит', NULL, 128, NULL, 'iPhone', 999.00, 'SN-PH-001', '6.1 дюйма', NULL, '44444444-4444-4444-4444-444444444441'),
    ('ddddddd2-dddd-dddd-dddd-dddddddddddd', TRUE, 'Синий', NULL, 64, NULL, 'iPhone', 799.00, 'SN-PH-002', '6.1 дюйма', NULL, '44444444-4444-4444-4444-444444444441'),
    ('eeeeeee1-eeee-eeee-eeee-eeeeeeeeeeee', TRUE, 'Серебристый', NULL, NULL, NULL, 'MacBook', 2400.00, 'SN-PC-001', '16 дюймов', 'M2 Pro', '55555555-5555-5555-5555-555555555551'),
    ('eeeeeee2-eeee-eeee-eeee-eeeeeeeeeeee', TRUE, 'Золотой', NULL, NULL, NULL, 'MacBook', 1200.00, 'SN-PC-002', '13 дюймов', 'M1', '55555555-5555-5555-5555-555555555551');

-- Вставка данных в таблицу devices
INSERT INTO devices VALUES
    ('11111111-1111-1111-1111-111111111111', TRUE, 'Samsung', 'Корея', 'Samsung QLED', TRUE, 'TV'),
    ('11111111-1111-1111-1111-111111111112', FALSE, 'LG', 'Корея', 'LG OLED', TRUE, 'TV'),
    ('11111111-1111-1111-1111-111111111113', TRUE, 'Sony', 'Япония', 'Sony Bravia', FALSE, 'TV'),
    ('22222222-2222-2222-2222-222222222221', FALSE, 'Dyson', 'Англия', 'Dyson V15', TRUE, 'VACUUM'),
    ('22222222-2222-2222-2222-222222222222', TRUE, 'Xiaomi', 'Китай', 'Xiaomi Mi Vacuum', TRUE, 'VACUUM'),
    ('22222222-2222-2222-2222-222222222223', TRUE, 'Samsung', 'Корея', 'Samsung Jet 90', FALSE, 'VACUUM'),
    ('33333333-3333-3333-3333-333333333331', TRUE, 'LG', 'Корея', 'LG InstaView', FALSE, 'FRIDGE'),
    ('33333333-3333-3333-3333-333333333332', FALSE, 'Samsung', 'Корея', 'Samsung Family Hub', TRUE, 'FRIDGE'),
    ('33333333-3333-3333-3333-333333333333', TRUE, 'Bosch', 'Германия', 'Bosch VitaFresh', TRUE, 'FRIDGE'),
    ('44444444-4444-4444-4444-444444444441', TRUE, 'Apple', 'США', 'iPhone 14', TRUE, 'SMARTPHONE'),
    ('44444444-4444-4444-4444-444444444442', FALSE, 'Samsung', 'Корея', 'Samsung Galaxy S22', TRUE, 'SMARTPHONE'),
    ('44444444-4444-4444-4444-444444444443', TRUE, 'Google', 'США', 'Google Pixel 7', FALSE, 'SMARTPHONE'),
    ('55555555-5555-5555-5555-555555555551', TRUE, 'Apple', 'США', 'MacBook Pro', TRUE, 'PC'),
    ('55555555-5555-5555-5555-555555555552', FALSE, 'Dell', 'США', 'Dell XPS', TRUE, 'PC'),
    ('55555555-5555-5555-5555-555555555553', TRUE, 'Lenovo', 'Китай', 'Lenovo ThinkPad', FALSE, 'PC'),
    ('5447833c-f397-4769-9059-c0c4fff7fb1b', TRUE, 'Sony', 'Япония', 'Sony OLED TV', TRUE, 'TV');
