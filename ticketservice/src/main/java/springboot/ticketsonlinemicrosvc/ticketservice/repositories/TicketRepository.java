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
 */
public interface TicketRepository extends JpaRepository<TicketEntity, Long>
{
  List<TicketEntity> findByTicketPrice(Integer ticketPrice);

  List<TicketEntity> findByTicketPriceLessThan(Integer ticketPrice);

  List<TicketEntity> findByTicketPriceBetween(Integer minPrice, Integer maxPrice);

  // pt++ : referenced object in query param : must be done on contoller level as this microservece has the ticket table ONLY
  //  List<TicketEntity> findByEventAndTicketPrice( EventEntity event, Integer ticketPrice); // pt++ : referenced object in query param

  List<TicketEntity> findByEventId(Long eventId);

  // pt++ : referenced object in query param : must be done on contoller level as this microservece has the ticket table ONLY
  // List<TicketEntity> findByEventName( String name);
}
