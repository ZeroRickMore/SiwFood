INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Amore', '/immaginiIngredienti/amore.jpg', 'cucchiai')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Carne Vegetariana', '/immaginiIngredienti/carne_vegetariana_2.jpg', 'unità')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Carote', '/immaginiIngredienti/carote.jpg', 'unità')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Cipolla', '/immaginiIngredienti/cipolla.jpg', 'spicchi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Farina', '/immaginiIngredienti/farina.jpg', 'grammi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Forza', '/immaginiIngredienti/forza.png', 'cucchiai')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Latte', '/immaginiIngredienti/latte.jpg', 'bicchieri')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Olio', '/immaginiIngredienti/olio.jpg', 'cucchiai')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Parmigiano', '/immaginiIngredienti/parmigiano.jpg', 'grammi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Pasta Generica', '/immaginiIngredienti/pasta_generica.jpg', 'grammi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Piadina', '/immaginiIngredienti/piadina.jpg', 'unità')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Piselli', '/immaginiIngredienti/piselli.jpg', 'grammi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Pomodori', '/immaginiIngredienti/pomodori.jpeg', 'unità')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Prosciutto', '/immaginiIngredienti/prosciutto.jpg', 'etti')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Ricotta', '/immaginiIngredienti/ricotta.jpg', 'grammi')
INSERT INTO ingrediente (id, nome, image_path, unità_di_misura) VALUES(nextval('ingrediente_seq'), 'Sale', '/immaginiIngredienti/sale.jpg', 'cucchiai')




INSERT INTO cuoco (id, nome, cognome, data_nascita, fotografia_path) VALUES(nextval('cuoco_seq'), 'Goku', 'Fighissimo', '737-04-16', '/immaginiCuochi/goku_poderoso_upscayl.jpg')
INSERT INTO cuoco (id, nome, cognome, data_nascita, fotografia_path) VALUES(nextval('cuoco_seq'), 'Vegeta', 'Vegetariano', '732-09-19', '/immaginiCuochi/vegeta.jpg')





INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'Carne Vegetariana', (SELECT id FROM cuoco WHERE nome='Vegeta' AND cognome='Vegetariano'), 'Carne estremamente vegetariana, solo per veri vegetariani!', '/immaginiRicette/carne_vegetariana/carne_vegetariana.jpg,/immaginiRicette/carne_vegetariana/carne_vegetariana_2.jpg,/immaginiRicette/carne_vegetariana/carne_vegetariana_3.jpg')
INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'Pasta Fighissima', (SELECT id FROM cuoco WHERE nome='Goku' AND cognome='Fighissimo'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ', '/immaginiRicette/pasta_fighissima/pasta_fighissima.jpg,/immaginiRicette/pasta_fighissima/pasta_fighissima_2.jpg')
INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'Piadina Fighissima', (SELECT id FROM cuoco WHERE nome='Goku' AND cognome='Fighissimo'), 'Piadina DAVVERO fighissima, la piadina che rende immortali, pura forza!!', '/immaginiRicette/piadina_fighissima/piadina_fighissima.jpg,/immaginiRicette/piadina_fighissima/piadina_fighissima_2.jpg,/immaginiRicette/piadina_fighissima/piadina_fighissima_3.png')

INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione, tutti_path_delle_immagini_string) VALUES(nextval('ricetta_seq'), 'I Sapovi Della Mia Tevva', (SELECT id FROM cuoco WHERE nome='Vegeta' AND cognome='Vegetariano'), 'Gnocchi di ricotta fresca con vellutata di piselli freschi', '/immaginiRicette/i_sapovi_della/i_sapovi_della.jpg,/immaginiRicette/i_sapovi_della/i_sapovi_della_2.jpg')



/*I sapovi della mia tevva*/
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (1000, (SELECT id FROM ingrediente WHERE nome='Amore'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (200, (SELECT id FROM ingrediente WHERE nome='Farina'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (1, (SELECT id FROM ingrediente WHERE nome='Latte'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (3, (SELECT id FROM ingrediente WHERE nome='Olio'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (200, (SELECT id FROM ingrediente WHERE nome='Piselli'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (100, (SELECT id FROM ingrediente WHERE nome='Ricotta'), (SELECT id FROM ricetta WHERE nome_ricetta='I Sapovi Della Mia Tevva'));

/*Piadina fighissima*/
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (7, (SELECT id FROM ingrediente WHERE nome='Cipolla'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (1000, (SELECT id FROM ingrediente WHERE nome='Forza'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (20, (SELECT id FROM ingrediente WHERE nome='Piadina'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (18, (SELECT id FROM ingrediente WHERE nome='Pomodori'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (400, (SELECT id FROM ingrediente WHERE nome='Prosciutto'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (74, (SELECT id FROM ingrediente WHERE nome='Ricotta'), (SELECT id FROM ricetta WHERE nome_ricetta='Piadina Fighissima'));


/*Pasta Fighissima*/
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (5, (SELECT id FROM ingrediente WHERE nome='Cipolla'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (200, (SELECT id FROM ingrediente WHERE nome='Forza'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (3, (SELECT id FROM ingrediente WHERE nome='Olio'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (60, (SELECT id FROM ingrediente WHERE nome='Parmigiano'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (500, (SELECT id FROM ingrediente WHERE nome='Pasta Generica'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (5, (SELECT id FROM ingrediente WHERE nome='Pomodori'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Sale'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta Fighissima'));


/*Carne Vegetariana*/
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (1, (SELECT id FROM ingrediente WHERE nome='Carne Vegetariana'), (SELECT id FROM ricetta WHERE nome_ricetta='Carne Vegetariana'));








