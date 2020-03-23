create database GC
go

USE GC
GO

create table Contas
(
    id                bigint identity  not null,
    dataCriacao       date             not null,
    flagAtivo         bit              not null,
    limiteSaqueDiario double precision not null,
    saldo             double precision not null,
    tipoConta         int              not null,
    pessoa_id         bigint           not null,
    primary key (id)
)
GO

create table Pessoas
(
    id             bigint identity not null,
    cpf            varchar(11)     not null,
    dataNascimento date            not null,
    nome           varchar(255)    not null,
    primary key (id)
)
GO

create table Transacoes
(
    id            bigint identity  not null,
    dataTransacao datetime2        not null,
    tipoTransacao int              not null,
    valor         double precision not null,
    conta_id      bigint           not null,
    pessoa_id     bigint,
    primary key (id)
)
GO

alter table Pessoas
    add constraint UK_gej40f8jfd5efnwlggtpwjloo unique (cpf)
GO
alter table Contas
    add constraint FKhqr9va259wuhkhcdxidiag5is
        foreign key (pessoa_id)
            references Pessoas
GO
alter table Transacoes
    add constraint FK80r643wwhljgr3t6v0yitrlvn
        foreign key (conta_id)
            references Contas
GO
alter table Transacoes
    add constraint FKe7kcjm8sevke4w7249mpbnd5c
        foreign key (pessoa_id)
            references Pessoas
GO
insert
into Pessoas
    (cpf, dataNascimento, nome)
values ('44090988888', '1995-06-29', 'Lucas Souza Pessoa')
GO