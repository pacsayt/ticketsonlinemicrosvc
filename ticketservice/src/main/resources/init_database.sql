drop table if exists ticket CASCADE;

DROP SEQUENCE drop sequence IF EXISTS if exists hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE ticket
(
  ticket_id BIGINT NOT NUL,
  seat_no INTEGER,
  ticket_price INTEGER,
  event_id BIGINT NOT NULL,
  PRIMARY KEY (ticket_id)
);