DROP TABLE IF EXISTS event;

DROP SEQUENCE drop sequence IF EXISTS if exists hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event
(
  event_id BIGINT NOT NULL,
  date TIMESTAMP,
  name VARCHAR(255),
  event_place_id BIGINT,
  PRIMARY KEY (event_id)
);

ALTER TABLE event ADD CONSTRAINT UK_EVENT_NAME UNIQUE (date, event_place_id);