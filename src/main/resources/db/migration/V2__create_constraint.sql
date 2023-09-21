ALTER TABLE usuarios_roles
ADD CONSTRAINT fk_usuario_role
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id);

ALTER TABLE usuarios_roles
ADD CONSTRAINT fk_role_usuario
    FOREIGN KEY (id_rol) REFERENCES roles (id);

ALTER TABLE topicos
ADD CONSTRAINT fk_topico_curso
    FOREIGN KEY (id_curso) REFERENCES cursos (id);

ALTER TABLE topicos
ADD CONSTRAINT fk_topico_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id);

ALTER TABLE discusiones
ADD CONSTRAINT fk_discucion_topico
    FOREIGN KEY (id_topico) REFERENCES topicos (id);

ALTER TABLE discusiones
ADD CONSTRAINT fk_discucion_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id);