-- http://localhost:8012/h2-console/

-- insert into event values( 11, parsedatetime('03-09-2020 11:32:41.00', 'dd-MM-yyyy hh:mm:ss.SS'), 'EventName_11', 11); pt++ : never tested
-- Hibernate: create table event (event_id bigint not null, date timestamp, event_place_id bigint, name varchar(255), primary key (event_id))
insert into event values( 11, '2020-09-03 11:32:41.00', 11, 'EventName_11');
insert into event values( 22, '2020-09-03 11:32:41.00', 22, 'EventName_22');
insert into event values( 33, '2020-09-03 11:32:41.00', 33, 'EventName_33');

insert into event values( 44, '2020-09-03 11:32:41.00', 11, 'EventName_44'); -- pt++ : same event place for two events