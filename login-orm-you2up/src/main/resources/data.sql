INSERT INTO Treino (periodo)
VALUES ('noturno'),
       ('vespertino');

INSERT INTO local_treino_usuario
    (nome, numero, rua, bairro, cidade, uf, is_academia)
VALUES
    ('Academia do Zé', 123, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Academia do João', 456, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Academia do Pedro', 789, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', true),
    ('Praça da luz', 101, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', false),
    ('Praça dos alunos', 112, 'Rua dos Bobos', 'Centro', 'São Paulo', 'SP', false);

INSERT INTO usuario
(nome, email, senha, data_nascimento, descricao, sexo, meta_treinos, estagio, local_treino_id_local_treino)
VALUES
    ('Steven Spielberg', 'steven@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '2000-11-03', 'Sou um usuário legal', 'm', 100, 'iniciante', 1),
    ('Christopher Nolan', 'christopher@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1999-05-21',  'Sou um usuário legal', 'm', 70, 'intermediario', 2),
    ('Quentin Tarantino', 'quaentin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1945-02-11',  'Sou um usuário legal', 'm', 80, 'avancado', 3),
    ('Alfred Hitchcock', 'alfred@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-07-03',  'Sou um usuário legal', 'm', 110, 'intermediario', 4),
    ('Martin Scorsese', 'martin@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1998-11-30',  'Sou um usuário legal', 'm', 60, 'iniciante', 5);

INSERT INTO notificacao
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
