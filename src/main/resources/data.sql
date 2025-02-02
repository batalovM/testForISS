CREATE EXTENSION IF NOT EXISTS "pgcrypto";
INSERT INTO devices (id, name, manufacturer_country, manufacturer_company, online_order_available, installment_available, type)
VALUES
    (gen_random_uuid(), 'Samsung TV', 'South Korea', 'Samsung', TRUE, TRUE, 'TV'),
    (gen_random_uuid(), 'Dyson Vacuum', 'UK', 'Dyson', TRUE, FALSE, 'VACUUM'),
    (gen_random_uuid(), 'Apple iPhone', 'USA', 'Apple', TRUE, TRUE, 'SMARTPHONE');

INSERT INTO models (id, name, serial_number, color, size, price, availability, category, device_id)
VALUES
    (gen_random_uuid(), 'Samsung TV Model A', 'SN12345A', 'Black', 'Large', 599.99, TRUE, 'TV', (SELECT id FROM devices WHERE name = 'Samsung TV')),
    (gen_random_uuid(), 'Samsung TV Model B', 'SN12345B', 'White', 'Medium', 699.99, TRUE, 'TV', (SELECT id FROM devices WHERE name = 'Samsung TV'));

INSERT INTO models (id, name, serial_number, color, size, price, availability, category, dust_container_volume, mode_count, device_id)
VALUES
    (gen_random_uuid(), 'Dyson Vacuum Model A', 'SN67890A', 'Red', 'Small', 299.99, TRUE, 'VACUUM', 0.5, 3, (SELECT id FROM devices WHERE name = 'Dyson Vacuum')),
    (gen_random_uuid(), 'Dyson Vacuum Model B', 'SN67890B', 'Blue', 'Medium', 349.99, TRUE, 'VACUUM', 1.0, 5, (SELECT id FROM devices WHERE name = 'Dyson Vacuum'));

INSERT INTO models (id, name, serial_number, color, size, price, availability, category, memory, camera_count, processor_type, device_id)
VALUES
    (gen_random_uuid(), 'Apple iPhone Model A', 'SN54321A', 'Silver', 'Small', 999.99, TRUE, 'SMARTPHONE', 128, 2, 'A14', (SELECT id FROM devices WHERE name = 'Apple iPhone')),
    (gen_random_uuid(), 'Apple iPhone Model B', 'SN54321B', 'Gold', 'Medium', 1099.99, TRUE, 'SMARTPHONE', 256, 3, 'A15', (SELECT id FROM devices WHERE name = 'Apple iPhone'));

