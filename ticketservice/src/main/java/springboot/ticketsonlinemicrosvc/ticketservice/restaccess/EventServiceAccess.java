package springboot.ticketsonlinemicrosvc.ticketservice.restaccess;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;

/**
 * http://localhost:8013/ticket/
 */
@Component
public class EventServiceAccess
{
  private static final Logger LOG = LoggerFactory.getLogger( EventServiceAccess.class);

  public static final String EVENTSERVICE_URL = "http://eventservice/event/";

  @Autowired
  private RestTemplate restTemplate;

  public Event getById( Long eventId)
  {
    LOG.info( "EventServiceAccess::getById( " + eventId + ") ++++++++++++++++++++");
    return restTemplate.getForObject( EVENTSERVICE_URL + eventId, Event.class);
  }

  public Event post( Event eventToSave)
  {
    LOG.info( "EventServiceAccess::post( " + eventToSave.getId() + ") ++++++++++++++++++++");

    return restTemplate.postForObject( EVENTSERVICE_URL, eventToSave, Event.class);
  }

  public void put( Event eventToSave)
  {
    LOG.info( "EventServiceAccess::put( " + eventToSave.getId() + ") ++++++++++++++++++++");

    restTemplate.put( EVENTSERVICE_URL, eventToSave, Event.class);
  }

  public Events getAll()
  {
    LOG.info( "EventServiceAccess::getAll() ++++++++++++++++++++");

//    ResponseEntity<Event> responseEntity = restTemplate.getForEntity( EVENTSERVICE_URL, Event.class);
//
//    LOG.info( "EventServiceAccess::getAll() : responseEntity.getStatusCode()=" + responseEntity.getStatusCode() + "++++++++++++++++++++");

    return restTemplate.getForObject( EVENTSERVICE_URL, Events.class);
  }
}