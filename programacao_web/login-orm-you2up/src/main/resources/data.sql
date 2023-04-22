INSERT INTO Treino (periodo)
VALUES ('noturno'),
       ('vespertino');

INSERT INTO Usuario
(nome, email, senha, data_nascimento, nota_media, descricao, sexo, meta_treinos, estagio)
VALUES
    ('Steven Spielberg', 'steven@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-11-03', 3.0, 'Sou um usuário legal', 'm', 100, 'iniciante' ),
    ('Christopher Nolan', 'christopher@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1999-05-21', 5.0, 'Sou um usuário legal', 'm', 70, 'intermediario'),
    ('Quentin Tarantino', 'quaentin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1945-02-11', 4.0, 'Sou um usuário legal', 'm', 80, 'avancado'),
    ('Alfred Hitchcock', 'alfred@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-07-03', 4.6, 'Sou um usuário legal', 'm', 110, 'intermediario'),
    ('Martin Scorsese', 'martin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-11-30', 3.0, 'Sou um usuário legal', 'm', 60, 'iniciante');

INSERT INTO Notificacao
    (conteudo,data_hora, usuario_id)
VALUES
    ('Você tem um novo treino no momento', '2023-12-21 17:20:00', 1);

INSERT INTO treino_has_usuario
    (inicio_treino, is_realizado, quantidade_treinos, treino_id, usuario_id)
VALUES
    ('2023-10-10 20:00:00', false, 1, 1, 1),
    ('2023-10-10 20:00:00', false, 1, 1, 2),
    ('2023-12-21 17:20:00', false, 1, 2, 3),
    ('2023-12-21 17:20:00', false, 1, 2, 4),
    ('2023-12-21 17:20:00', false, 1, 2, 5);
