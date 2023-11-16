CREATE DATABASE you2up;
USE you2up;

-- Tabela local_treino_usuario
CREATE TABLE local_treino_usuario (
    id_local_treino INTEGER PRIMARY KEY AUTO_INCREMENT,
    bairro  VARCHAR(255),
    cidade VARCHAR(255),
    is_academia BOOLEAN,
    nome VARCHAR(255),
    numero INTEGER,
    rua VARCHAR(255),
    uf VARCHAR(255)
);

-- SELECT * FROM usuario;

-- Tabela usuario
CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY AUTO_INCREMENT,
    data_criacao_conta TIMESTAMP,
    data_nascimento DATE,
    descricao VARCHAR(255),
    email VARCHAR(255),
    estagio VARCHAR(255),
    meta_treinos INTEGER,
    nome VARCHAR(255),
    senha VARCHAR(255),
    local_treino_id_local_treino INTEGER,
    FOREIGN KEY (local_treino_id_local_treino) REFERENCES local_treino_usuario(id_local_treino)
);

-- Tabela treino
CREATE TABLE treino (
    id_treino INTEGER PRIMARY KEY AUTO_INCREMENT,
    is_realizado BOOLEAN,
    periodo VARCHAR(255)
);

-- Tabela avaliacao
CREATE TABLE avaliacao (
    id_avaliacao INTEGER PRIMARY KEY AUTO_INCREMENT,
    nota DECIMAL(3,1),
    avaliado_id_usuario INTEGER,
    avaliador_id_usuario INTEGER,
    treino_id_treino INTEGER,
    FOREIGN KEY (avaliado_id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (avaliador_id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (treino_id_treino) REFERENCES treino(id_treino)
);

-- Tabela foto
CREATE TABLE foto (
    id_foto INTEGER PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255),
    usuario_id_usuario INTEGER,
    FOREIGN KEY (usuario_id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela foto_perfil
CREATE TABLE foto_perfil (
    id_foto_perfil INTEGER PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255),
    usuario_id_usuario INTEGER,
    FOREIGN KEY (usuario_id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela tb_match
CREATE TABLE tb_match (
    id_match INTEGER PRIMARY KEY AUTO_INCREMENT,
    data_match TIMESTAMP,
    is_ativo BOOLEAN,
    usuario1_id_usuario INTEGER,
    usuario2_id_usuario INTEGER,
    FOREIGN KEY (usuario1_id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (usuario2_id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela notificacao
CREATE TABLE notificacao (
    id_notificacao INTEGER PRIMARY KEY AUTO_INCREMENT,
    conteudo VARCHAR(255),
    data_hora TIMESTAMP,
    usuario_id_usuario INTEGER,
    FOREIGN KEY (usuario_id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela treino_has_usuario
CREATE TABLE treino_has_usuario (
    inicio_treino TIMESTAMP,
    treino_id_treino INTEGER,
    usuario_id_usuario INTEGER,
    PRIMARY KEY (inicio_treino, treino_id_treino, usuario_id_usuario),
    FOREIGN KEY (treino_id_treino) REFERENCES treino(id_treino),
    FOREIGN KEY (usuario_id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO local_treino_usuario (bairro, cidade, is_academia, nome, numero, rua, uf) VALUES
    ('Centro', 'São Paulo', true, 'Academia A', 123, 'Rua A', 'SP'),
    ('Bairro X', 'São Paulo', false, 'Praça de Esportes B', 456, 'Rua B', 'SP'),
    ('Subúrbio', 'São Paulo', true, 'Ginásio C', 789, 'Rua C', 'SP'),
    ('Zona Sul', 'São Paulo', false, 'Campo D', 101, 'Rua D', 'SP'),
    ('Centro', 'São Paulo', true, 'Academia E', 222, 'Rua E', 'SP'),
    ('Bairro Y', 'São Paulo', false, 'Quadra F', 333, 'Rua F', 'SP'),
    ('Vila Alegre', 'São Paulo', true, 'Complexo Esportivo G', 444, 'Rua G', 'SP'),
    ('Zona Norte', 'São Paulo', false, 'Campo H', 555, 'Rua H', 'SP'),
    ('Bairro Z', 'São Paulo', true, 'Academia I', 666, 'Rua I', 'SP'),
    ('Periferia', 'São Paulo', false, 'Quadra J', 777, 'Rua J', 'SP');

-- Inserir dados na tabela usuario
INSERT INTO usuario (data_criacao_conta, data_nascimento, descricao, email, estagio, meta_treinos, nome, senha, local_treino_id_local_treino) VALUES
    ('2023-01-01', '1990-05-15', 'Entusiasta do Fitness', 'usuario1@email.com', 'Iniciante', 30, 'Alice', 'senha123', 1),
    ('2023-02-15', '1985-08-20', 'Amante de Esportes', 'usuario2@email.com', 'Intermediário', 45, 'Bob', 'senha456', 3),
    ('2023-03-30', '1995-03-10', 'Atleta Profissional', 'usuario3@email.com', 'Avançado', 60, 'Charlie', 'senha789', 5),
    ('2023-04-18', '1988-11-25', 'Entusiasta do Fitness', 'usuario4@email.com', 'Iniciante', 30, 'David', 'senhaabc', 2),
    ('2023-05-22', '1992-07-03', 'Amante de Esportes', 'usuario5@email.com', 'Intermediário', 45, 'Eva', 'senhaxyz', 4),
    ('2023-06-11', '1980-09-08', 'Atleta Profissional', 'usuario6@email.com', 'Avançado', 60, 'Frank', 'senha456', 6),
    ('2023-07-09', '1998-02-14', 'Entusiasta do Fitness', 'usuario7@email.com', 'Iniciante', 30, 'Grace', 'senha123', 8),
    ('2023-08-14', '1983-04-30', 'Amante de Esportes', 'usuario8@email.com', 'Intermediário', 45, 'Henry', 'senha789', 10),
    ('2023-09-25', '1994-06-22', 'Atleta Profissional', 'usuario9@email.com', 'Avançado', 60, 'Ivy', 'senhaabc', 7),
    ('2023-10-08', '1987-10-18', 'Entusiasta do Fitness', 'usuario10@email.com', 'Iniciante', 30, 'Jack', 'senhaxyz', 9);

-- Inserir dados na tabela treino
INSERT INTO treino (is_realizado, periodo) VALUES
    (true, 'Manhã'),
    (false, 'Tarde'),
    (true, 'Noite'),
    (false, 'Manhã'),
    (true, 'Tarde'),
    (false, 'Noite'),
    (true, 'Manhã'),
    (false, 'Tarde'),
    (true, 'Noite'),
    (false, 'Manhã');

-- Inserir dados na tabela avaliacao
INSERT INTO avaliacao (nota, avaliado_id_usuario, avaliador_id_usuario, treino_id_treino) VALUES
    (4.5, 1, 2, 1),
    (3.2, 2, 3, 2),
    (5.0, 3, 4, 3),
    (2.8, 4, 5, 4),
    (4.1, 5, 6, 5),
    (3.7, 6, 7, 6),
    (4.9, 7, 8, 7),
    (3.5, 8, 9, 8),
    (4.2, 9, 10, 9),
    (3.8, 10, 1, 10);

-- Inserir dados na tabela foto
INSERT INTO foto (url, usuario_id_usuario) VALUES
    ('foto1.jpg', 1),
    ('foto2.jpg', 2),
    ('foto3.jpg', 3),
    ('foto4.jpg', 4),
    ('foto5.jpg', 5),
    ('foto6.jpg', 6),
    ('foto7.jpg', 7),
    ('foto8.jpg', 8),
    ('foto9.jpg', 9),
    ('foto10.jpg', 10);

-- Inserir dados na tabela foto_perfil
INSERT INTO foto_perfil (url, usuario_id_usuario) VALUES
    ('perfil1.jpg', 1),
    ('perfil2.jpg', 2),
    ('perfil3.jpg', 3),
    ('perfil4.jpg', 4),
    ('perfil5.jpg', 5),
    ('perfil6.jpg', 6),
    ('perfil7.jpg', 7),
    ('perfil8.jpg', 8),
    ('perfil9.jpg', 9),
    ('perfil10.jpg', 10);

-- Inserir dados na tabela tb_match
INSERT INTO tb_match (data_match, is_ativo, usuario1_id_usuario, usuario2_id_usuario) VALUES
    ('2023-01-02', true, 1, 2),
    ('2023-02-20', true, 2, 3),
    ('2023-04-01', true, 3, 4),
    ('2023-04-20', true, 4, 5),
    ('2023-05-25', true, 5, 6),
    ('2023-06-15', true, 6, 7),
    ('2023-07-10', true, 7, 8),
    ('2023-08-15', true, 8, 9),
    ('2023-10-01', true, 9, 10),
    ('2023-10-10', true, 10, 1);

-- Inserir dados na tabela notificacao
INSERT INTO notificacao (conteudo, data_hora, usuario_id_usuario) VALUES
    ('Você recebeu uma mensagem.', '2023-01-03 08:30:00', 1),
    ('Novo desafio disponível.', '2023-02-21 15:45:00', 2),
    ('Parabéns! Você atingiu sua meta de treinos.', '2023-04-02 18:20:00', 3),
    ('Receba dicas de alimentação saudável.', '2023-04-21 11:10:00', 4),
    ('Novo treino personalizado disponível.', '2023-05-26 14:55:00', 5),
    ('Avalie seu último treino.', '2023-06-16 09:40:00', 6),
    ('Desafio semanal: Corrida de 10 km.', '2023-07-11 07:00:00', 7),
    ('Atualize suas metas de treino.', '2023-08-16 16:30:00', 8),
    ('Participe do torneio de esportes.', '2023-10-02 12:15:00', 9),
    ('Convide amigos para treinar juntos.', '2023-10-11 10:05:00', 10);

-- Inserir dados na tabela treino_has_usuario
INSERT INTO treino_has_usuario (inicio_treino, treino_id_treino, usuario_id_usuario) VALUES
    ('2023-01-02 09:00:00', 1, 1),
    ('2023-02-20 17:30:00', 2, 2),
    ('2023-04-01 10:00:00', 3, 3),
    ('2023-04-20 08:45:00', 4, 4),
    ('2023-05-25 14:00:00', 5, 5),
    ('2023-06-15 16:30:00', 6, 6),
    ('2023-07-10 12:00:00', 7, 7),
    ('2023-08-15 18:15:00', 8, 8),
    ('2023-10-01 07:30:00', 9, 9),
    ('2023-10-10 11:45:00', 10, 10);

SELECT * FROM avaliacao;
SELECT * FROM foto;
SELECT * FROM foto_perfil;
SELECT * FROM local_treino_usuario;
SELECT * FROM treino;
SELECT * FROM treino_has_usuario;
SELECT * FROM usuario;