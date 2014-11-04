# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table part (
  id                        integer not null,
  name                      varchar(255),
  part_number               varchar(255),
  price                     float,
  vendor_id                 integer,
  constraint pk_part primary key (id))
;

create table project (
  id                        integer not null,
  name                      varchar(255),
  completed                 float,
  price                     float,
  constraint pk_project primary key (id))
;

create table project_part (
  id                        integer not null,
  state                     varchar(255),
  project_id                integer,
  part_id                   integer,
  constraint pk_project_part primary key (id))
;

create table vendor (
  id                        integer not null,
  name                      varchar(255),
  url                       varchar(255),
  constraint pk_vendor primary key (id))
;

create sequence part_seq;

create sequence project_seq;

create sequence project_part_seq;

create sequence vendor_seq;

alter table part add constraint fk_part_vendor_1 foreign key (vendor_id) references vendor (id) on delete restrict on update restrict;
create index ix_part_vendor_1 on part (vendor_id);
alter table project_part add constraint fk_project_part_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_project_part_project_2 on project_part (project_id);
alter table project_part add constraint fk_project_part_part_3 foreign key (part_id) references part (id) on delete restrict on update restrict;
create index ix_project_part_part_3 on project_part (part_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists part;

drop table if exists project;

drop table if exists project_part;

drop table if exists vendor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists part_seq;

drop sequence if exists project_seq;

drop sequence if exists project_part_seq;

drop sequence if exists vendor_seq;

