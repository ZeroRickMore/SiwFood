INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Cipolla')
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Amicizia')
INSERT INTO ingrediente (id, nome) VALUES(nextval('ingrediente_seq'), 'Pressa idraulica')

INSERT INTO cuoco (id, nome, cognome, data_nascita) VALUES(nextval('cuoco_seq'), 'Goku', 'Fighissimo', '737-04-16')
INSERT INTO cuoco (id, nome, cognome, data_nascita) VALUES(nextval('cuoco_seq'), 'Vegeta', 'Vegetariano', '732-09-19')

INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione) VALUES(nextval('ricetta_seq'), 'Pasta fighissima', (SELECT id FROM cuoco WHERE nome='Goku' AND cognome='Fighissimo'), 'Pasta DAVVERO fighissima!')
INSERT INTO ricetta (id, nome_ricetta, cuoco_id, descrizione) VALUES(nextval('ricetta_seq'), 'Carne vegetariana', (SELECT id FROM cuoco WHERE nome='Vegeta' AND cognome='Vegetariano'), 'Carne DAVVERO vegetariana!')

INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Cipolla'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta fighissima'))
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Amicizia'), (SELECT id FROM ricetta WHERE nome_ricetta='Pasta fighissima'))
INSERT INTO ricetta_ingrediente2quantità (quantità, ingrediente2quantity_key, ricetta_id) VALUES (2, (SELECT id FROM ingrediente WHERE nome='Pressa idraulica'), (SELECT id FROM ricetta WHERE nome_ricetta='Carne vegetariana'))
