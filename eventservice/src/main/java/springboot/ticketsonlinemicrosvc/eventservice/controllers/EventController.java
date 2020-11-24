package springboot.ticketsonlinemicrosvc.eventservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;
import springboot.ticketsonlinemicrosvc.eventservice.services.EventService;

import java.util.Collections;
import java.util.Optional;

@RestController // pt++ currently works only in a class marked with @Component or @Service -> @Controller (+ @ResponseBody) -> @Component
@RequestMapping( path="event")
public class EventController
{
  private static final Logger LOG = LoggerFactory.getLogger( EventController.class);

  @Autowired
  private EventService eventService;

  @Value( "${parameter:default value}")
  private String parameter;

  @HystrixCommand( fallbackMethod = "getAllFallback", commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")}) // pt++ : "works with @Component or @Service"
  @GetMapping
  public Events getAll()
  {
    LOG.info( "EventController::getAll() +++++++++++++++++++++++++++++++");
    return new Events( eventService.findAll());
  }

  public Events getAllFallback()
  {
    LOG.info( "EventController::getAllFallback() +++++++++++++++++++++++++++++++");

    return new Events( Collections.<Event>emptyList());
  }

  @HystrixCommand( fallbackMethod = "getByIdFallback")
  @GetMapping( path="/{eventId}", produces = "application/json")
  public Optional<Event> getById(@PathVariable Long eventId)
  {
    LOG.info( "EventController::getById(" + eventId + ") +++++++++++++++++++++++++++++++");

    return eventService.findById( eventId);
  }

  public Optional<Event> getByIdFallback(@PathVariable Long eventId)
  {
    LOG.info( "EventController::getByIdFallback(" + eventId + ") +++++++++++++++++++++++++++++++");

    return Optional.empty();
  }


  @HystrixCommand( fallbackMethod = "postFallback")
  // <-> public ResponseEntity<Object> EventPlaceController::post( @RequestBody EventPlace eventPlace)
  @PostMapping // pt++ : POST - INSERT
  public ResponseEntity<Optional<Event>> post(@RequestBody Event event)
  {
    LOG.info( "EventController::post( " + event + ") +++++++++++++++++++++++++++++++");

    Event savedEvent = eventService.save( event);

    return ResponseEntity.ok( Optional.of( savedEvent));
  }

  public ResponseEntity<Optional<Event>> postFallback(@RequestBody Event event)
  {
    LOG.info( "EventController::postFallback( " + event + ") +++++++++++++++++++++++++++++++");


    return ResponseEntity.of( Optional.empty());
  }


  @HystrixCommand( fallbackMethod = "putFallback")
  @PutMapping // pt++ : PUT - update
  public ResponseEntity<Optional<Event>> put(@RequestBody Event event)
  {
    LOG.info( "EventController::put( " + event + ") +++++++++++++++++++++++++++++++");

    Event savedEvent = eventService.save( event);

    return ResponseEntity.ok( Optional.of( savedEvent));
  }

  public ResponseEntity<Optional<Event>> putFallback(@RequestBody Event event)
  {
    LOG.info( "EventController::putFallback( " + event + ") +++++++++++++++++++++++++++++++");

    return ResponseEntity.ok( Optional.empty());
  }

  @HystrixCommand( fallbackMethod = "getConfigFallback")
  @GetMapping( path = "/config")
  public String getConfig()
  {
    LOG.info( "EventController::getConfig() " + parameter + "+++++++++++++++++++++++++++++++");

    return parameter;
  }

  public String getConfigFallback()
  {
    LOG.info( "EventController::getConfigFallback()  +++++++++++++++++++++++++++++++");

    return "parameter_fallback_value";
  }
}