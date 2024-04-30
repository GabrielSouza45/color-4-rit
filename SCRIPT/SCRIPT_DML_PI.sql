USE color4rit;


SELECT * FROM JOGADOR;

INSERT INTO JOGADOR (NOME, LOGIN, SENHA) 
VALUES ('Admin', 'admin@admin.com', 'color#1234');


SELECT * FROM MUSICA;

INSERT INTO MUSICA (DURACAO, NOME, AUTOR) 
VALUES (12000,'Loretta', 'Ginger Root');


SELECT * FROM MAPA;

INSERT INTO MAPA (DIFICULDADE, FK_MUSICA) 
VALUES ('FACIL', 1);


SELECT * FROM NOTA;
SELECT * FROM NOTA WHERE STATUS = 1; -- pega apenas valores ativos

INSERT INTO NOTA (FK_MAPA, COR, TEMPO, STATUS) 
VALUES (1, 'VERMELHO', 12000, 1);


SELECT * FROM PLACAR;

INSERT INTO PLACAR (PONTUACAO, FK_JOGADOR, FK_MAPA)
VALUES (300, 1, 1);


UPDATE NOTA
SET STATUS = 0
WHERE ID = 3;

UPDATE MUSICA 
SET NOME = 'Loretta',
AUTOR = 'Ginger Root'
WHERE ID = 1;

UPDATE PLACAR 
SET PONTUACAO = 400 
WHERE ID = 1;

UPDATE JOGADOR 
SET NOME = 'Novo Nome' 
WHERE ID = 1;

UPDATE MAPA
SET DIFICULDADE = 'NOVA DIFICULDADE'
WHERE ID = 1;

DELETE FROM MUSICA WHERE ID = 1;

DELETE FROM NOTA 
WHERE ID = 1;

DELETE FROM MAPA 
WHERE ID = 1;

DELETE FROM PLACAR 
WHERE ID = 1;

DELETE FROM JOGADOR 
WHERE ID = 1;
