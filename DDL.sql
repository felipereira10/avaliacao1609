create table his_historico (
  his_id bigint generated always as identity,
  his_aut_nome_antigo varchar(20) not null,
  his_aut_nome_novo varchar(20) not null,
  his_data date not null,
  his_autorizacao bigint not null,
  his_alcance float,
  foreign key (his_autorizacao) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table usr_usuario (
  usr_id bigint generated always as identity,
  usr_nome varchar(20) not null,
  usr_senha varchar(150) not null,
  primary key (usr_id),
  unique (usr_nome)
);

create table ant_anotacao (
  ant_id bigint generated always as identity,
  ant_texto varchar(256) not null,
  ant_data_hora timestamp not null,
  ant_usr_id bigint not null,
  primary key (ant_id),
  foreign key (ant_usr_id) references usr_usuario(usr_id)
);

create table aut_autorizacao (
  aut_id bigint generated always as identity,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

-- tabela de associacao entre usuario e autorizacao
create table tra_trabalho (
  tra_id bigint generated always as identity,
  tra_titulo varchar(100) not null unique,
  tra_data_hora_entrega timestamp not null,
  tra_descricao varchar(200),
  tra_usr_id bigint not null,
  tra_nota int,
  foreign key (tra_usr_id) references usr_usuario (usr_id) on delete restrict on update cascade
);

create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

insert into usr_usuario (usr_nome, usr_senha)
  values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
  values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
  values (1, 1);
insert into ant_anotacao(ant_texto, ant_data_hora, ant_usr_id)
  values('Meu novo projeto', '2023-08-01 19:10', 1);
insert into tra_trabalho (tra_titulo, tra_data_hora_entrega, tra_nota, tra_usr_id)
  values ('Teste 1', current_timestamp, 6, 1),
         ('Teste 2', current_timestamp, null, 1);
insert into his_historico (his_aut_nome_antigo, his_aut_nome_novo, his_data, his_autorizacao, his_alcance)
  values ('ROLE_ROOT', 'ROLE_ADMIN', '2025-09-03', 1, 0.73),
         ('ROLE_USER', 'ROLE_GERAL', current_date, 1, null);

create user spring with password 'postgres';

grant update, delete, insert, select on all tables in schema public to spring;
grant usage on schema public to spring;
grant all privileges on all sequences in schema public to spring;

