-- Executed, if present see : hibernate: ddl-auto: none https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
DROP TABLE IF EXISTS event_place;

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event_place
(
  event_place_id BIGINT NOT NULL,
  name VARCHAR(255),
  no_of_seats INTEGER,
  PRIMARY KEY (event_place_id)
);

ALTER TABLE event_place ADD CONSTRAINT UK_EVENT_PLACE_NAME UNIQUE (name);