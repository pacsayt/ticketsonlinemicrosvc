package springboot.ticketsonlinemicrosvc.ticketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import java.util.List;

/**
 * H2 console : http://localhost:8080/h2-console/ -> JDBC URL : =jdbc:h2:mem:test
 * 
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
 *
 *  Session :
 *    Instances may exist in one of the following three states :
 *    transient − A new instance of a persistent class, which is not associated with a Session
 *                and has no representation in the database
 *    persistent − instance has a representation in the database, an identifier value and is
 *                 associated with a Session.
 *    detached −  the Hibernate Session is closed
 *
 *  Lots of Session's methods is listed :
 *    https://www.tutorialspoint.com/hibernate/hibernate_sessions.htm
 *
 *  Session session = factory.openSession();
 *
 *  Transaction tx = null;
 *  try {
 *     tx = session.beginTransaction();
 *     ...
 *     tx.commit();
 *  } catch (Exception e) {
 *     if (tx!=null) tx.rollback();
 *     e.printStackTrace();
 *  } finally {
 *     session.close();
 *  }
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