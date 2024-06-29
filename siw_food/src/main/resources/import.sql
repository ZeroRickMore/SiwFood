INSERT INTO ingrediente (id, nome, image_path) VALUES(nextval('ingrediente_seq'), 'Cipolla', 'immaginiIngredienti/cipolla.jpg')
INSERT INTO ingrediente (id, nome, image_path) VALUES(nextval('ingrediente_seq'), 'Carote', 'immaginiIngredienti/carote.jpg')
INSERT INTO ingrediente (id, nome, image_path) VALUES(nextval('ingrediente_seq'), 'Amore', 'immaginiIngredienti/amore.jpg')

INSERT INTO cuoco (id, nome, cognome, data_nascita, fotografia_path) VALUES(nextval('cuoco_seq'), 'Goku', 'Fighissimo', '737-04-16', 'immaginiCuochi/goku_poderoso_upscayl.jpg')
INSERT INTO cuoco (id, nome, cognome, data_nascita, fotografia_path) VALUES(nextval('cuoco_seq'), 'Vegeta', 'Vegetariano', '732-09-19', 'immaginiCuochi/vegeta.jpg')

INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'Pasta fighissima', (SELECT id FROM cuoco WHERE nome='Goku' AND cognome='Fighissimo'), 'Pasta DAVVERO fighissima!', 'immaginiRicette/carbonara.jpg')
INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'Carne vegetariana', (SELECT id FROM cuoco WHERE nome='Vegeta' AND cognome='Vegetariano'), 'Carne DAVVERO vegetariana!', 'immaginiRicette/carne_vegetariana.jpg')
INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'AAAAA', (SELECT id FROM cuoco WHERE nome='Vegeta' AND cognome='Vegetariano'), 'AAAAA DAVVERO vegetariana!', 'immaginiRicette/carne_vegetariana.jpg')

INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Cipolla'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta fighissima'))
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Carote'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta fighissima'))
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Amore'), (SELECT id FROM ricetta WHERE nome_ricetta='Carne vegetariana'))
