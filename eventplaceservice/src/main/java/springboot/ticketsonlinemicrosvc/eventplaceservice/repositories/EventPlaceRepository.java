package springboot.ticketsonlinemicrosvc.eventplaceservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;

import java.util.List;

/**
 * pt++ : http://localhost:8011/h2-console/
 *
 * pt++ : Hibernate: save, persist, update, merge, saveOrUpdate
 * pt++ : https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
 * ---------------------------------------------------------------------------------------------------------------------
 * Spring Boot With H2 Database
 * https://www.baeldung.com/spring-boot-h2-database
 *
 * pt++ : H2 console : http://localhost:8080/h2-console/ -> JDBC URL : =jdbc:h2:mem:test
 *
 * Session :
 * Instances may exist in one of the following three states :
 * transient − A new instance of a persistent class, which is not associated with a Session
 * and has no representation in the database
 * persistent − instance has a representation in the database, an identifier value and is
 * associated with a Session.
 * detached −  the Hibernate Session is closed

 * Lots of Session's methods is listed :
 * https://www.tutorialspoint.com/hibernate/hibernate_sessions.htm

 * Session session = factory.openSession();
 * Transaction tx = null;

 * try {
 *   tx = session.beginTransaction();
 *   ...
 *   tx.commit();
 * }
 * catch (Exception e) {
 *   if (tx!=null) tx.rollback();
 *   e.printStackTrace();
 * }
 * finally {
 *   session.close();
 * }
 */
@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long>
{
  List<EventPlace> findByNameContainingIgnoreCase(String name);

  List<EventPlace> findFirst2ByNameContainingIgnoreCase(String name);
  // public abstract java.util.List **.repositories.EventPlaceRepository.findFirst2ByNameContainingIgnoreCase(java.lang.String)!

  List<EventPlace> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

  // Sort sort = new Sort(Sort.Direction.ASC, "name");
  List<EventPlace> findByNameContains(String name, Sort sort);
}