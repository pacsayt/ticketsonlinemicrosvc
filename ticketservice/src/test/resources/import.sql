-- Hibernate support src/test
-- o.h.t.schema.internal.SchemaCreatorImpl  : HHH000476: Executing import script 'file:/home/tamas/projektek/TicketsOnline/ticketsonline/target/test-classes/import.sql'
insert into event_place values( 11, 'Name_11', 11);
insert into event_place values( 22, 'Name_22', 22);
insert into event_place values( 33, 'Name_33', 33);

-- insert into event values( 11, parsedatetime('03-09-2020 11:32:41.00', 'dd-MM-yyyy hh:mm:ss.SS'), 'EventName_11', 11); pt++ : never tested
insert into event values( 11, '2020-09-03 11:32:41.00', 'EventName_11', 11);
insert into event values( 22, '2020-09-03 11:32:41.00', 'EventName_22', 22);
insert into event values( 33, '2020-09-03 11:32:41.00', 'EventName_33', 33);

insert into event values( 44, '2020-09-03 11:32:41.00', 'EventName_44', 11); -- pt++ : same event place for two events

-- ticket (ticket_id bigint not null, seat_no integer, ticket_price integer, event_id bigint not null)
insert into ticket values( 10, 2, 100, 11);
insert into ticket values( 11, 1, 111, 11);
insert into ticket values( 12, 3, 112, 11);
insert into ticket values( 13, 4, 113, 11);

insert into ticket values( 22, 2, 122, 22);
insert into ticket values( 33, 3, 133, 33);

-- bookedticket (bookedticket_id bigint not null, booked_ticket_ticket_id bigint
insert into bookedticket values( 22, 22);

insert into bookedticket values( 33, 33);