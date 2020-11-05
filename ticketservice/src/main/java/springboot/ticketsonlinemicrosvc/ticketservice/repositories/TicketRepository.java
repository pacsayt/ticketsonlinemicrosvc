package springboot.ticketsonlinemicrosvc.ticketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import java.util.List;

/**
 * pt++ :
 * Derived Queries with Spring Data JPA – The Ultimate Guide
 * https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/
 *
 * Hibernate Logging Guide – Use the right config for development and production
 * https://thorben-janssen.com/hibernate-logging-guide/
 *
 * https://thorben-janssen.com/jpql/
 * https://thorben-janssen.com/spring-data-jpa-query-annotation/
 * https://thorben-janssen.com/jpa-native-queries/
 * https://thorben-janssen.com/how-to-join-unrelated-entities/ *
 *
 * JpaRepository< , > -> CrudRepository< , >
 */
public interface TicketRepository extends JpaRepository<TicketEntity, Long>
{
  List<TicketEntity> findByTicketPrice( Integer ticketPrice);

  List<TicketEntity> findByTicketPriceLessThan( Integer ticketPrice);

  List<TicketEntity> findByTicketPriceBetween( Integer minPrice, Integer maxPrice);

  // pt++ : referenced object in query param : must be done on contoller level as this microservece has the ticket table ONLY
  List<TicketEntity> findByEventIdAndTicketPrice( Long eventId, Integer ticketPrice); // pt++ : referenced object in query param

  List<TicketEntity> findByEventId( Long eventId);
}
// most majd lehet @Query() native true/false-t tesztelni