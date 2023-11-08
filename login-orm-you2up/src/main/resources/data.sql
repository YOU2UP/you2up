INSERT INTO Treino (periodo, is_realizado)
VALUES ('noturno', false),
       ('vespertino', false);

INSERT INTO local_treino_usuario
    (nome, numero, rua, bairro, cidade, uf, is_academia)
VALUES
    ('Academia do Zé', 123, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Academia do João', 456, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Academia do Pedro', 789, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Praça da luz', 101, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', false),
    ('Praça dos alunos', 112, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', false);

INSERT INTO usuario (nome, email, senha, data_nascimento, descricao, meta_treinos, estagio, local_treino_id_local_treino, data_criacao_conta)
VALUES
    ('Steven Spielberg', 'steven@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-11-03', 'Sou um usuário legal', 100, 'iniciante', 1, '2023-11-08 10:00:00'),
    ('Christopher Nolan', 'christopher@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1999-05-21', 'Sou um usuário legal', 70, 'intermediario', 2, '2023-11-08 10:15:00'),
    ('Quentin Tarantino', 'quaentin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1945-02-11', 'Sou um usuário legal', 80, 'avancado', 3, '2023-11-08 10:30:00'),
    ('Alfred Hitchcock', 'alfred@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-07-03', 'Sou um usuário legal', 110, 'intermediario', 4, '2023-11-08 10:45:00'),
    ('Martin Scorsese', 'martin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-11-30', 'Sou um usuário legal', 60, 'iniciante', 5, '2023-11-08 11:00:00');

INSERT INTO notificacao
    (conteudo,data_hora, usuario_id_usuario)
VALUES
    ('Você tem um novo treino no momento', '2023-12-21 17:20:00', 1);

INSERT INTO treino_has_usuario
    (inicio_treino, treino_id_treino, usuario_id_usuario)
VALUES
    ('2023-10-10 20:00:00', 1, 1),
    ('2023-10-10 20:00:00', 1, 2),
    ('2023-12-21 17:20:00', 2, 3),
    ('2023-12-21 17:20:00', 2, 4),
    ('2023-12-21 17:20:00', 2, 5);

INSERT INTO avaliacao
    (nota, avaliador_id_usuario, avaliado_id_usuario, treino_id_treino)
VALUES
    (5, 1, 2, 1),
    (1.5, 2, 1, 1),
    (3.3, 3, 4, 2),
    (2.7, 3, 5, 2),
    (3, 4, 3, 2),
    (4.5, 4, 5, 2),
    (3, 5, 3, 2),
    (2, 5, 4, 2);

INSERT INTO match
    (data_match, is_ativo ,usuario1_id_usuario, usuario2_id_usuario)
VALUES
    ('2023-10-10 20:00:00', true, 1, 2),
    ('2023-11-11 20:00:00', true, 2, 3),
    ('2023-12-03 20:00:00', true, 3, 5),
    ('2023-09-04 20:00:00', true, 4, 2),
    ('2023-09-23 20:00:00', true, 1, 4),
    ('2023-08-22 20:00:00', true, 5, 3);

INSERT INTO foto (url, usuario_id_usuario)
VALUES
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 1),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 2),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 3),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 4),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 5),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 1),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 2),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 3),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 4),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 5),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 1),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 2),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 3),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 4),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 5),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 1);

INSERT INTO foto_perfil (url, usuario_id_usuario)
VALUES
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 1),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 2),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 3),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 4),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 5);

UPDATE usuario
SET foto_perfil_id_foto_perfil = 1
WHERE id_usuario = 1;

UPDATE usuario
SET foto_perfil_id_foto_perfil = 2
WHERE id_usuario= 2;

UPDATE usuario
SET foto_perfil_id_foto_perfil = 3
WHERE id_usuario = 3;

UPDATE usuario
SET foto_perfil_id_foto_perfil = 4
WHERE id_usuario = 4;

UPDATE usuario
SET foto_perfil_id_foto_perfil = 5
WHERE id_usuario = 5;


