-- Hibernate support src/test

-- insert into event values( 11, parsedatetime('03-09-2020 11:32:41.00', 'dd-MM-yyyy hh:mm:ss.SS'), 'EventName_11', 11); pt++ : never tested
insert into event values( 11, '2020-09-03 11:32:41.00', 'EventName_11', 11);
insert into event values( 22, '2020-09-03 11:32:41.00', 'EventName_22', 22);
insert into event values( 33, '2020-09-03 11:32:41.00', 'EventName_33', 33);

insert into event values( 44, '2020-09-03 11:32:41.00', 'EventName_44', 11); -- pt++ : same event place for two events