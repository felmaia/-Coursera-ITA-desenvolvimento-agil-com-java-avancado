CREATE TABLE usuario
(
  login varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  nome varchar(50) NOT NULL,
  senha varchar(50) NOT NULL,
  pontos int NOT NULL,
  CONSTRAINT usuario_pk PRIMARY KEY (login)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE topico
(
    id_topico int NOT NULL AUTO_INCREMENT,
    login varchar(50) NOT NULL,
    titulo varchar(255) NOT NULL,
    conteudo varchar(1000),
    CONSTRAINT topico_pk PRIMARY KEY (id_topico)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE topico ADD CONSTRAINT topico_usuario_fk FOREIGN KEY (login)
    REFERENCES usuario (login);

CREATE TABLE comentario
(
    id_comentario int NOT NULL AUTO_INCREMENT,
    login varchar(50) NOT NULL,
    id_topico int  NOT NULL,
    comentario varchar(1000),
    CONSTRAINT comentario_pk PRIMARY KEY (id_comentario)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE comentario ADD CONSTRAINT comentario_usuario_fk FOREIGN KEY (login)
    REFERENCES usuario (login);

ALTER TABLE comentario ADD CONSTRAINT comentario_topico_fk FOREIGN KEY (id_topico)
    REFERENCES topico (id_topico);