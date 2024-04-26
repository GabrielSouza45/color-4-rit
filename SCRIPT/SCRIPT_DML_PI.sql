USE color4rit;

SELECT * FROM jogador;

INSERT INTO jogador (nome, login, senha)
VALUES ('admin', 'admin@admin.com', 'color#1234');


SELECT * FROM musica;

INSERT INTO musica (duracao, nome, autor)
VALUES ('00:02:55','tropa do kusterverso', 'loren tralha');


SELECT * FROM mapa;

INSERT INTO mapa (dificuldade, fk_musica)
VALUES ('facil', 1);


SELECT * FROM nota;

INSERT INTO nota (fk_mapa, cor, tempo)
VALUES (1, 'azul', '00:00:15');


SELECT * FROM placar;

INSERT INTO placar (pontuacao, fk_jogador, fk_musica)
VALUES (300, 1, 1);



UPDATE musica 
SET nome = 'nova musica' 
WHERE id = 1;

UPDATE placar 
SET pontuacao = 400 
WHERE id = 1;

UPDATE jogador 
SET nome = 'novo nome' 
WHERE id = 1;

UPDATE mapa
SET dificuldade = 'nova dificuldade'
WHERE id = 1;

DELETE FROM musica 
WHERE id = 1;

DELETE FROM nota 
WHERE id = 1;

DELETE FROM mapa 
WHERE id = 1;

DELETE FROM placar 
WHERE id = 1;

DELETE FROM Jogador 
WHERE id = 1;
