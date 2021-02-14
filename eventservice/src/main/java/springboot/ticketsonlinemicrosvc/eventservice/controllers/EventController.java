package springboot.ticketsonlinemicrosvc.eventservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;
import springboot.ticketsonlinemicrosvc.eventservice.services.EventService;

import java.util.Collections;
import java.util.Optional;

/**
 * Zuul : http://localhost:8761/eventplaceservice/eventplace/
 *
 * http://localhost:8012/event/
 * http://localhost:8012/event/11
 *
 * http://localhost:8012/event/config : prints out the parameter got from configuration server
 *
 * http://localhost:8012/actuator/refresh/
 *
 * the configuration values are read on the client’s startup ONLY
 * /actuator/refresh : triggers fetching them again <-> @RefreshScope
 *
 * http://localhost:8012/ -> enter : https://localhost:8012/hystrix.stream
 *
 */

@RefreshScope   // https://spring.io/guides/gs/centralized-configuration/
@RestController // pt++ currently works only in a class marked with @Component or @Service -> @Controller (+ @ResponseBody) -> @Component
@RequestMapping( path="event")
public class EventController
{
  private static final Logger LOG = LoggerFactory.getLogger( EventController.class);

  @Autowired
  private EventService eventService;

  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
  @Value( "${parameter:parameter - DEFAULT value}")
  private String parameter;

  @Value("${shared_parameter:sharedParameter - DEFAULT value}")
  private String sharedParameter;

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
    LOG.info( "EventController::getConfig() " + parameter + "\n" + sharedParameter );

    return "parameter : "  + parameter + "\n sharedParameter : " + sharedParameter;
  }

  public String getConfigFallback()
  {
    LOG.info( "EventController::getConfigFallback()  +++++++++++++++++++++++++++++++");

    return "parameter_fallback_value";
  }
}