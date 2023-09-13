-- -----------------------------------------------------
-- Schema you2up
-- -----------------------------------------------------

-- Cria o schema "you2up" se não existir
CREATE SCHEMA IF NOT EXISTS `you2up` DEFAULT CHARACTER SET utf8 ;

-- Utiliza o schema "you2up"
USE `you2up`;

-- -----------------------------------------------------
-- Table `localTreinoUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `localTreinoUsuario` (
  `id_local_treino` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `rua` VARCHAR(70) NULL,
  `bairro` VARCHAR(70) NULL,
  `cidade` VARCHAR(70) NULL,
  `uf` CHAR(2) NULL,
  `is_academia` TINYINT NULL,
  PRIMARY KEY (`id_local_treino`)
);

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` INT NOT NULL,
  `nome` VARCHAR(70) NULL,
  `email` VARCHAR(70) NULL,
  `senha` VARCHAR(45) NULL,
  `data_nascimento` DATE NULL,
  `descricao` VARCHAR(100) NULL,
  `estagio` VARCHAR(45) NULL,
  `sexo` CHAR(1) NULL,
  `meta_treinos` INT NULL,
  `fkLocalTreino` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  FOREIGN KEY (`fkLocalTreino`) REFERENCES `localTreinoUsuario` (`id_local_treino`)
);

-- -----------------------------------------------------
-- Table `treino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treino` (
  `id_treino` INT NOT NULL,
  `periodo` ENUM('matutino', 'vespertino', 'noturno') NULL,
  `preferencia` VARCHAR(45) NULL,
  PRIMARY KEY (`id_treino`)
);

-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat` (
  `id_mensagem` INT NOT NULL,
  `texto` MEDIUMTEXT NULL,
  `usuario1` INT NOT NULL,
  `usuario2` INT NOT NULL,
  `data_hora` DATETIME NOT NULL,
  PRIMARY KEY (`id_mensagem`),
  FOREIGN KEY (`usuario1`) REFERENCES `usuario` (`id_usuario`),
  FOREIGN KEY (`usuario2`) REFERENCES `usuario` (`id_usuario`)
);

-- -----------------------------------------------------
-- Table `match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `match` (
  `id_match` INT NOT NULL,
  `data_match` DATE NULL,
  `is_ativo` TINYINT NULL,
  `usuario1` INT NOT NULL,
  `usuario2` INT NOT NULL,
  `chat_id` INT NOT NULL,
  PRIMARY KEY (`id_match`),
  FOREIGN KEY (`usuario1`) REFERENCES `usuario` (`id_usuario`),
  FOREIGN KEY (`usuario2`) REFERENCES `usuario` (`id_usuario`),
  FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id_mensagem`)
);

-- -----------------------------------------------------
-- Table `treino_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treino_has_usuario` (
  `treino_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `inicio_treino` DATETIME NULL,
  `quantiidade_treinos` INT NULL,
  `is_realizado` TINYINT NULL,
  PRIMARY KEY (`treino_id`, `usuario_id`),
  FOREIGN KEY (`treino_id`) REFERENCES `treino` (`id_treino`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
);

-- -----------------------------------------------------
-- Table `avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `avaliacao` (
  `id_avaliacao` INT NOT NULL,
  `nota` DOUBLE NULL,
  `avaliador` INT NOT NULL,
  `avaliado` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  FOREIGN KEY (`avaliador`) REFERENCES `usuario` (`id_usuario`),
  FOREIGN KEY (`avaliado`) REFERENCES `usuario` (`id_usuario`)
);

-- -----------------------------------------------------
-- Table `notificacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notificacao` (
  `id_notificacao` INT NOT NULL,
  `conteudo` VARCHAR(500) NULL,
  `data_hora` DATETIME NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_notificacao`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
);
