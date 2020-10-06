package springboot.ticketsonlinemicrosvc.eventservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonlinemicrosvc.eventservice.controllers.EventController;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.eventservice.repositories.EventRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
  private static final Logger LOG = LoggerFactory.getLogger( EventController.class);

  @Autowired
  private EventRepository eventRepository;

  public Long count()
  {
    return eventRepository.count();
  }

  public Event save(Event EventToSave)
  {
    return eventRepository.save( EventToSave);
  }

//    EventRepository.findOne(Example<Event> EventExample)

  public Optional<Event> findById( Long iD)
  {
    Optional<Event> eventOptional = eventRepository.findById( iD);

LOG.info( "EventService::findById( " + iD + ") -> " + eventOptional.isPresent());

    return eventOptional;
  }

  public List<Event> findAll()
  {
    return eventRepository.findAll();
  }

  public List<Event> findByName( String name)
  {
    return eventRepository.findByName( name);
  }

  public List<Event> findByDate( Timestamp date)
  {
    return eventRepository.findByDate( date);
  }


  public Optional<Event> findByNameAndDate( String name, Timestamp date)
  {
    return eventRepository.findByNameAndDate( name, date);
  }

  public void delete( Event  EventToBeDeleted)
  {
    eventRepository.delete( EventToBeDeleted);
  }

  public Event getOne( Long iD)
  {
    return eventRepository.getOne( iD);
  }
}