INSERT INTO users VALUES (1, 'admin', '$2a$10$4yB3di.lJb3/Uh6edRwAlOHU/h31PrcEssd79oS0sNmIHDmUAw9Wu', '1');
INSERT INTO authorities VALUES (1, 'admin', 'ROLE_ADMIN');
INSERT INTO room_type VALUES (1, "executive", "executive room only for 1 person", "", 30, 1000, 1612373010587, 1612373010587);
INSERT INTO customer VALUES (1, 'customer', '$2a$10$4yB3di.lJb3/Uh6edRwAlOHU/h31PrcEssd79oS0sNmIHDmUAw9Wu', 'Customer', 1612373010587, 1612373010587,4000);
INSERT INTO reservation VALUES (1, 1, 1, 5, 1612373010587, 1612373010587, FALSE, 1612373010587, 1612373010587,"Booked",2000);
