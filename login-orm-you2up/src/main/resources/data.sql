INSERT INTO Treino (periodo, is_realizado)
VALUES ('noturno', false),
       ('vespertino', false),
       ('matutino', true),
       ('noturno', true);


INSERT INTO local_treino_usuario
    (nome, numero, rua, bairro, cidade, uf, is_academia)
VALUES
    ('Academia do Zé', 123, 'Rua dos Bobos', 'Centro', 'São Bernardo', 'SP', true),
    ('Academia do João', 456, 'Rua das Flores', 'Centro', 'São Bernardo', 'SP', true),
    ('Academia do Pedro', 789, 'Rua dos Girassóis', 'Centro', 'Santo André', 'SP', true),
    ('Praça da Luz', 101, 'Avenida Paulista', 'Jardins', 'São Paulo', 'SP', false),
    ('Praça dos Alunos', 112, 'Rua dos Estudantes', 'Centro', 'São Paulo', 'SP', false),
    ('Ginásio Municipal', 35, 'Avenida Atlântica', 'Copacabana', 'Rio de Janeiro', 'RJ', true),
    ('Parque Central', 88, 'Avenida Brasil', 'Botafogo', 'Rio de Janeiro', 'RJ', false),
    ('Praça da República', 99, 'Avenida Paulista', 'Centro', 'São Paulo', 'SP', false);


INSERT INTO usuario (nome, email, senha, data_nascimento, descricao, meta_treinos, estagio, local_treino_id_local_treino)
VALUES
    ('Steven Spielberg', 'steven@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-11-03', 'Sou um usuário legal', 100, 'iniciante', 1),
    ('Christopher Nolan', 'christopher@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1999-05-21', 'Sou um usuário legal', 70, 'intermediario', 2),
    ('Quentin Tarantino', 'quaentin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1945-02-11', 'Sou um usuário legal', 80, 'avancado', 3),
    ('Alfred Hitchcock', 'alfred@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-07-03', 'Sou um usuário legal', 110, 'intermediario', 4),
    ('Martin Scorsese', 'martin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-11-30', 'Sou um usuário legal', 60, 'iniciante', 5);

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
    ('2023-12-21 17:20:00', 2, 5),
    ('2023-11-11 20:00:00', 3, 1),
    ('2023-11-11 20:00:00', 3, 2),
    ('2023-12-03 20:00:00', 4, 1),
    ('2023-12-03 20:00:00', 4, 2),
    ('2023-12-03 20:00:00', 4, 3);

INSERT INTO avaliacao
    (nota, avaliador_id_usuario, avaliado_id_usuario, treino_id_treino)
VALUES
    (4, 1, 2, 3),
    (3, 1, 3, 3),
    (5, 1, 4, 3),
    (4, 1, 5, 3),
    (3, 2, 1, 3),
    (4, 2, 3, 3),
    (5, 2, 4, 3),
    (4, 2, 5, 3),
    (3, 3, 1, 3),
    (4, 3, 2, 3),
    (5, 3, 4, 3),
    (4, 3, 5, 3),
    (3, 4, 1, 3),
    (4, 4, 2, 3),
    (5, 4, 3, 3),
    (4, 4, 5, 3),
    (3, 5, 1, 3),
    (4, 5, 2, 3),
    (5, 5, 3, 3),
    (4, 5, 4, 3),
    (3, 1, 2, 4),
    (4, 1, 3, 4),
    (5, 1, 4, 4),
    (4, 1, 5, 4),
    (3, 2, 1, 4),
    (4, 2, 3, 4),
    (5, 2, 4, 4),
    (4, 2, 5, 4),
    (3, 3, 1, 4),
    (4, 3, 2, 4),
    (5, 3, 4, 4);


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

-- Novos usuários
INSERT INTO usuario (nome, email, senha, data_nascimento, descricao, meta_treinos, estagio, local_treino_id_local_treino)
VALUES
    ('James Cameron', 'james@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1954-08-16', 'Sou um usuário legal', 90, 'iniciante', 6),
    ('Stanley Kubrick', 'stanley@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1928-07-26', 'Sou um usuário legal', 120, 'avancado', 7),
    ('Francis Ford Coppola', 'francis@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1939-04-07', 'Sou um usuário legal', 80, 'iniciante', 8);

-- Novos treinos
INSERT INTO Treino (periodo, is_realizado)
VALUES
    ('vespertino', false),
    ('matutino', true),
    ('noturno', true),
    ('matutino', false);

-- Novas avaliações
INSERT INTO avaliacao
(nota, avaliador_id_usuario, avaliado_id_usuario, treino_id_treino)
VALUES
    (3, 1, 6, 4),
    (5, 2, 6, 2),
    (4, 3, 6, 3),
    (4, 4, 6, 1);

-- Novas associações de usuário a treinos
INSERT INTO treino_has_usuario
(inicio_treino, treino_id_treino, usuario_id_usuario)
VALUES
    ('2023-10-10 20:00:00', 5, 6),
    ('2023-10-10 20:00:00', 5, 1),
    ('2023-10-10 20:00:00', 6, 6),
    ('2023-12-21 17:20:00', 7, 6),
    ('2023-12-21 17:20:00', 7, 1),
    ('2023-12-21 17:20:00', 7, 2);


INSERT INTO foto (url, usuario_id_usuario)
VALUES
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 6),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 6),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 7),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 7),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 8),
    ('https://static.todamateria.com.br/upload/pa/is/paisagem-natural-og.jpg', 8);

INSERT INTO foto_perfil (url, usuario_id_usuario)
VALUES
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 6),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 7),
    ('https://www.promoview.com.br/uploads/images/unnamed%2819%29.png', 8);

UPDATE usuario SET foto_perfil_id_foto_perfil = 6 WHERE id_usuario = 6;
UPDATE usuario SET foto_perfil_id_foto_perfil = 7 WHERE id_usuario = 7;
UPDATE usuario SET foto_perfil_id_foto_perfil = 8 WHERE id_usuario = 8;

