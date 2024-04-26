USE color4rit;

SELECT * FROM Jogador;

INSERT INTO Jogador (nome, login, senha)
VALUES ('admin', 'admin@admin.com', 'color#1234');


SELECT * FROM Musica;

INSERT INTO Musica (duracao, nome, autor)
VALUES ('00:02:55','tropa do kusterverso', 'loren tralha');


SELECT * FROM Mapa;

INSERT INTO Mapa (dificuldade, fk_musica)
VALUES ('facil', 1);


SELECT * FROM Nota;

INSERT INTO Nota (fk_mapa, cor, tempo)
VALUES (1, 'azul', '00:00:15');


SELECT * FROM Placar;

INSERT INTO Placar (pontuacao, fk_jogador, fk_musica)
VALUES (300, 1, 1);



UPDATE Musica 
SET nome = 'nova musica' 
WHERE id = 1;

UPDATE Placar 
SET pontuacao = 400 
WHERE id = 1;

UPDATE Jogador 
SET nome = 'novo nome' 
WHERE id = 1;

UPDATE Mapa
SET dificuldade = 'nova dificuldade'
WHERE id = 1;

DELETE FROM Musica 
WHERE id = 1;

DELETE FROM Nota 
WHERE id = 1;

DELETE FROM Mapa 
WHERE id = 1;

DELETE FROM Placar 
WHERE id = 1;

DELETE FROM Jogador 
WHERE id = 1;
