DROP TABLE IF EXISTS event_place;

DROP SEQUENCE drop sequence IF EXISTS if exists hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event_place
(
  event_place_id BIGINT NOT NULL,
  name VARCHAR(255),
  no_of_seats INTEGER,
  PRIMARY KEY (event_place_id)
);

ALTER TABLE event_place ADD CONSTRAINT UK_EVENT_PLACE_NAME UNIQUE (name);