package springboot.ticketsonlinemicrosvc.eventplaceservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlaces;
import springboot.ticketsonlinemicrosvc.eventplaceservice.configuration.EventPlaceConfiguration;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.EventPlaceService;

import java.util.Collections;
import java.util.Optional;

/**
 * Zuul : http://localhost:8761/eventplaceservice/eventplace/
 *
 * callEndpoints.sh :
 * ~~~~~~~~~~~~~~~~~~
 * http://localhost:8011/eventplace/
 * http://localhost:8011/eventplace/11
 *
 * http://localhost:8011/eventplace/config : prints out the parameter got from configuration server
 *
 * http://localhost:8011/actuator/refresh/ : (type=Not Found, status=404)
 *
 * the configuration values are read on the client’s startup ONLY
 * /actuator/refresh : triggers fetching them again <-> @RefreshScope
 *
 * http://localhost:8011/ -> enter : https://localhost:8011/hystrix.stream
 *
 */

@RefreshScope                       // pt++ : not needed for @ConfigurationProperties
                                    // https://spring.io/guides/gs/centralized-configuration/
@RestController                     // pt++ : you cannot specify root path here (it seems)
@RequestMapping( path="eventplace") // pt++ : root path must be specified separately path == value aliasses
public class EventPlaceController
{
//  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
//  @Value( "${parameter: parameter - DEFAULT value}")
//  private String parameter;
//
//  @Value("${shared_parameter: sharedParameter - DEFAULT value}")
//  private String sharedParameter;
//
  @Autowired
  private EventPlaceConfiguration eventPlaceConfiguration;

  @Autowired
  private EventPlaceService eventPlaceService;

  @GetMapping()
  @HystrixCommand(fallbackMethod = "getAllFallback")
  public EventPlaces getAll()
  {
    return new EventPlaces( eventPlaceService.findAll());
  }

  public EventPlaces getAllFallback()
  {
    return new EventPlaces( Collections.emptyList()); // List<EventPlace>.findAll()
  }

  @GetMapping( path="/{eventPlaceId}", produces = "application/json")
  @HystrixCommand(fallbackMethod = "getByIdFallback")
  public EventPlace getById(@PathVariable Long eventPlaceId)
  {
    return eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace( -1L, "", -1));
  }

  public EventPlace getByIdFallback( @PathVariable Long eventPlaceId)
  {
    return eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace( -1L, "", -1));
  }

  @PostMapping() // pt++ : POST - INSERT
  @HystrixCommand(fallbackMethod = "postFallback")
  public ResponseEntity<Object> post( @RequestBody EventPlace eventPlace)
  {
    //Generate resource id
    EventPlace eventPlaceSaved = eventPlaceService.save( eventPlace);

    // Create resource location : no idea what it is good for
//    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                              .path("/{id}")
//                                              .buildAndExpand( eventPlaceSaved.getId())
//                                              .toUri();

    Optional<EventPlace> optionalEventPlace = Optional.of( eventPlaceSaved);

    //Send location in response
    return ResponseEntity/*.created( location)*/.ok( optionalEventPlace); // pt++ : this works
  }

  public ResponseEntity<Object> postFallback( @RequestBody EventPlace eventPlace)
  {
    return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @PutMapping() // pt++ : PUT - update
  @HystrixCommand(fallbackMethod = "putFallback")
  public ResponseEntity<Optional<EventPlace>> put(@RequestBody EventPlace eventPlace)
  {
    EventPlace eventPlaceAfterSave = eventPlaceService.save( eventPlace);

    return ResponseEntity.ok( Optional.of( eventPlaceAfterSave));
  }

  public ResponseEntity<Object> putFallback( @RequestBody EventPlace eventPlace)
  {
    return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @DeleteMapping()
  @HystrixCommand(fallbackMethod = "putFallback")
  public void delete( @RequestBody EventPlace eventPlace)
  {
    eventPlaceService.delete( eventPlace);
  }

  @RequestMapping( path = "/config")
  public String getConfig()
  {
    return "parameter : "  + eventPlaceConfiguration.getParameter() + "\n sharedParameter : " + eventPlaceConfiguration.getSharedParameter();
  }
}
