package springboot.ticketsonlinemicrosvc.eventplaceservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.eventplaceservice.repositories.EventPlaceRepository;

import java.util.List;
import java.util.Optional;


/**
 * LazyInitializationException: could not initialize proxy [springboot.ticketsonline.entities.EventPlace#11] - no Session
 * When use getOne and findOne methods Spring Data JPA
 *
 * Optional<T> findById(ID id) (name in the new API) relies on EntityManager.find() that performs an entity eager loading.
 *
 * T getOne(ID id) relies on EntityManager.getReference() that performs an entity lazy loading.
 * So to ensure the effective loading of the entity, invoking a method on it is required.
 *
 * https://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
 */
@Service
public class EventPlaceService
{
  private static final Logger LOG = LoggerFactory.getLogger( EventPlaceService.class);

  @Autowired
  private EventPlaceRepository eventPlaceRepository;

  public Long count()
  {
    return eventPlaceRepository.count();
  }

  public EventPlace save(EventPlace eventPlaceToSave)
  {
    EventPlace eventPlace = eventPlaceRepository.save( eventPlaceToSave);

    LOG.info( "EventPlaceService::save() : " + eventPlace + "++++++++++++++++++++++++");

    return eventPlace;
  }

  public Optional<EventPlace> findById(Long iD)
  {
    LOG.info( "EventPlaceService::findById(" + iD + ") ++++++++++++++++++++++++");

    Optional<EventPlace> eventPlaceOptional = eventPlaceRepository.findById( iD);

    return eventPlaceOptional;
  }

  public List<EventPlace> findAll()
  {
    LOG.info( "EventPlaceService::findAll() ++++++++++++++++++++++++");

    return eventPlaceRepository.findAll();
  }

  public void delete( EventPlace  eventPlaceToBeDeleted)
  {
    eventPlaceRepository.delete( eventPlaceToBeDeleted);
  }

//  public List<EventPlace> findByNameContainingIgnoreCase( String name)
//  {
//    return eventPlaceRepository.findByNameContainingIgnoreCase( name);
//  }

//  public List<EventPlace> findFirst2ByNameContainingIgnoreCase( String name)
//  {
//    return eventPlaceRepository.findFirst2ByNameContainingIgnoreCase( name);
//  }

//  public List<EventPlace> findByNameContainingIgnoreCaseOrderByNameAsc( String name)
//  {
//    return eventPlaceRepository.findByNameContainingIgnoreCaseOrderByNameAsc( name);
//  }

  public EventPlace getOne( Long iD)
  {
    return eventPlaceRepository.getOne( iD);
  }
}
