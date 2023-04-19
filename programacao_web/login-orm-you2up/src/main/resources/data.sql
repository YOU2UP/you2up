INSERT INTO Treino (periodo, preferencia, inicio_treino)
VALUES ('noturno', 'preferencia', '2023-05-20 19:00:00'),
       ('vespertino', 'preferencia', '2023-05-25 15:00:00');

INSERT INTO Usuario
(nome, email, senha, data_nascimento, nota_media, descricao, treino_id)
VALUES
    ('Steven Spielberg', 'steven@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-11-03', 0.0, 'Sou um usuário legal', 1 ),
    ('Christopher Nolan', 'christopher@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1999-05-21', 0.0, 'Sou um usuário legal',2),
    ('Quentin Tarantino', 'quaentin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1945-02-11', 0.0, 'Sou um usuário legal',1),
    ('Alfred Hitchcock', 'alfred@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-07-03', 0.0, 'Sou um usuário legal',2),
    ('Martin Scorsese', 'martin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-11-30', 0.0, 'Sou um usuário legal',1);

INSERT INTO Notificacao
    (conteudo,data_hora, usuario_id)
VALUES
    ('banana', '2023-12-21 17:20:00', 1)