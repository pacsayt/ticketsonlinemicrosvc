DROP TABLE IF EXISTS bookedticket CASCADE;

CREATE TABLE bookedticket
(
  bookedticket_id BIGINT NOT NULL,
  booked_ticket_ticket_id BIGINT,
  PRIMARY KEY (bookedticket_id)
);