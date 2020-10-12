package springboot.ticketsonlinemicrosvc.eventservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlaces;
import springboot.ticketsonlinemicrosvc.eventservice.controllers.EventController;
import springboot.ticketsonlinemicrosvc.eventservice.repositories.EventRepository;
import springboot.ticketsonlinemicrosvc.eventservice.restaccess.EventPlaceServiceAccess;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService
{
  private static final Logger LOG = LoggerFactory.getLogger( EventController.class);

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  EventPlaceServiceAccess eventPlaceServiceAccess;

  public Long count()
  {
    return eventRepository.count();
  }

  public Event save( Event eventToSave)
  {
    EventPlace eventPlaceToSave = eventToSave.getEventPlace();

    Mono<EventPlace> eventPlaceSavedMono = eventPlaceServiceAccess.post( eventPlaceToSave);

    EventPlace eventPlaceSaved = eventPlaceSavedMono.block();

    EventEntity eventEntityToSave = new EventEntity( eventToSave, eventPlaceSaved.getId());

    EventEntity eventEntitySaved = eventRepository.save( eventEntityToSave);

    return new Event( eventEntitySaved, eventPlaceSaved);
  }

/* pt++ : for test ONLY
  public Optional<Event> findById(Long iD)
  {
    Optional<EventEntity> eventEntityOptional = eventRepository.findById(iD);

    LOG.info("EventService::findById( " + iD + ") -> EventEntity : " + eventEntityOptional.orElseGet(() -> new EventEntity()) + " +++++++++++++++++++++++++++++++++++++++++++++ ");

    if ( eventEntityOptional.isPresent() )
    {
      Long eventPlaceId = eventEntityOptional.get().getEventPlaceId();

      LOG.info("EventService::findById( " + iD + ") -> eventPlaceId : " + eventPlaceId + " +++++++++++++++++++++++++++++++++++++++++++++ ");

      Mono<EventPlace> entityMono = eventPlaceServiceAccess.getById( eventPlaceId);

      EventPlace eventPlace = entityMono.block();

      LOG.info("EventService::findById( " + iD + ") -> eventPlace : " + eventPlace + " +++++++++++++++++++++++++++++++++++++++++++++ ");

      return Optional.of( new Event( eventEntityOptional.get(), eventPlace));
    }

    return Optional.empty(); // pt++ : for test purposes ONLY
  }
*/

  // pt++ : RestTemplate version
  public Optional<Event> findById(Long iD)
  {
    Optional<EventEntity> eventEntityOptional = eventRepository.findById(iD);

    LOG.info("EventService::findById( " + iD + ") -> EventEntity : " + eventEntityOptional.orElseGet(() -> new EventEntity()) + " +++++++++++++++++++++++++++++++++++++++++++++ ");

    if ( eventEntityOptional.isPresent() )
    {
      Long eventPlaceId = eventEntityOptional.get().getEventPlaceId();

      LOG.info("EventService::findById( " + iD + ") -> eventPlaceId : " + eventPlaceId + " +++++++++++++++++++++++++++++++++++++++++++++ ");

      EventPlace eventPlace = eventPlaceServiceAccess.getById( eventPlaceId);

      LOG.info("EventService::findById( " + iD + ") -> eventPlace : " + eventPlace + " +++++++++++++++++++++++++++++++++++++++++++++ ");

      return Optional.of( new Event( eventEntityOptional.get(), eventPlace));
    }

    return Optional.empty(); // pt++ : for test purposes ONLY
  }


  /**
   * Reactor – How to convert Flux into List, Map
   * https://grokonez.com/reactive-programming/reactor/reactor-convert-flux-into-list-map-reactive-programming
   */
  public List<Event> findAll()
  {
    List<Event> allEvents = new ArrayList<>();

    Mono<EventPlaces> eventPlacesMono = eventPlaceServiceAccess.getAll();

    List<EventEntity> allEventEntities = eventRepository.findAll();

    EventPlaces eventPlaces = eventPlacesMono.block();

    for( EventEntity ee : allEventEntities )
    {
      for ( EventPlace ep : eventPlaces.getEventPlaces() )
      {
        if ( ee.getEventPlaceId().equals( ep.getId()))
        {
          allEvents.add( new Event( ee, ep));

          break;
        }
      }
    }

    return allEvents;
  }

  public List<Event> findByName(String name)
  {
//    List<EventEntity> eventEntitesFound = eventRepository.findByName( name);
//
//    return eventEntitiesToEvents( eventEntitesFound);
    return Collections.emptyList();
  }

  public List<Event> findByDate(Timestamp date)
  {
//    List<EventEntity> eventEntitesFound = eventRepository.findByDate( date);
//
//    return eventEntitiesToEvents( eventEntitesFound);
    return Collections.emptyList();
  }

  public Optional<Event> findByNameAndDate(String name, Timestamp date)
  {
/*
    Optional<EventEntity> optionalEventEntityFound = eventRepository.findByNameAndDate( name, date);

    if ( optionalEventEntityFound.isPresent() )
    {
      Mono<EventPlace> monoEventPlace = eventPlaceServiceAccess.getById( optionalEventEntityFound.get().getEventPlaceId());

      return Optional.of( new Event( optionalEventEntityFound.get(), monoEventPlace.block()));
    }
*/
    return Optional.empty();
  }

  public void delete( Event eventToBeDeleted)
  {
    eventRepository.delete( new EventEntity( eventToBeDeleted));
  }

  /**
   * pt++ : Difference between getOne and findById in Spring Data JPA?
   * getOne()
   * Lazily loaded reference to target entity
   * Useful only when access to properties of object is not required
   * Throws EntityNotFoundException if actual object does not exist at the time of access invocation
   * Better performance
   *
   * findById()
   * Actually loads the entity for the given id
   * Object is eagerly loaded so all attributes can be accessed
   * Returns null if actual object corresponding to given Id does not exist
   * An additional round-trip to database is required
   *
   * https://www.javacodemonk.com/difference-between-getone-and-findbyid-in-spring-data-jpa-3a96c3ff
   */
  public Event getOne(Long iD)
  {
//    // pt++ : using this way might give a slap the shit in the face ...
//    EventEntity eventEntityFound = eventRepository.getOne( iD);
//
//    Mono<EventPlace> monoEventPlace = eventPlaceServiceAccess.getById( eventEntityFound.getEventPlaceId());
//
//    return new Event( eventEntityFound, monoEventPlace.block());
    return null;
  }

//  private List<Event> eventEntitiesToEvents( List<EventEntity> eventEntities)
//  {
//    return eventEntities.stream().map( eventEntity -> {
//                                                        Mono<EventPlace> monoEventPlace = eventPlaceServiceAccess.getById( eventEntity.getEventPlaceId());
//                                                        return new Event( eventEntity, monoEventPlace.block());
//                                                      }).collect( Collectors.toList());
//  }
}