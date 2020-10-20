package springboot.ticketsonlinemicrosvc.eventplaceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;

import java.util.List;

/**
 * pt++ : Hibernate: save, persist, update, merge, saveOrUpdate
 * pt++ : https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
 */
@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long>
{
  List<EventPlace> findByNameContainingIgnoreCase(String name); // pt++ TODO : Did neither find a NamedQuery nor an annotated query for method

//  List<EventPlace> findFirst2ByNameContainingIgnoreCase(String name); // pt++ TODO : Did neither find a NamedQuery nor an annotated query for method
  // public abstract java.util.List **.repositories.EventPlaceRepository.findFirst2ByNameContainingIgnoreCase(java.lang.String)!

//  List<EventPlace> findByNameContainingIgnoreCaseOrderByNameAsc(String name); // pt++ TODO : Did neither find a NamedQuery nor an annotated query for method

  // Sort sort = new Sort(Sort.Direction.ASC, "name");
  // List<Book> findByNameContains(String name, Sort sort);
}