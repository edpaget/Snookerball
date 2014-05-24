# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "Games" ("id" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"first_player_id" BIGINT NOT NULL,"second_player_id" BIGINT NOT NULL);
create table "Players" ("id" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL);
create unique index "idx_name" on "Players" ("name");
alter table "Games" add constraint "first_player_fk" foreign key("first_player_id") references "Players"("id") on update NO ACTION on delete NO ACTION;
alter table "Games" add constraint "second_Player_fk" foreign key("second_player_id") references "Players"("id") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "Games" drop constraint "first_player_fk";
alter table "Games" drop constraint "second_Player_fk";
drop table "Games";
drop table "Players";

