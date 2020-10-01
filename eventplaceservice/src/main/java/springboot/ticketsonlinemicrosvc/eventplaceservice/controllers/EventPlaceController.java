package springboot.ticketsonlinemicrosvc.eventplaceservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.EventPlace;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.EventPlaces;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.EventPlaceService;

import java.util.Collections;
import java.util.Optional;

@RestController                     // pt++ : you cannot specify root path here (it seems)
@RequestMapping( path="eventplace") // pt++ : root path must be specified separately path == value aliasses
public class EventPlaceController
{
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
    EventPlace eventPlace = eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace( -1L, "", -1));

    return eventPlace;
  }

  public EventPlace getByIdFallback( @PathVariable Long eventPlaceId)
  {
    EventPlace eventPlace = eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace( -1L, "", -1));

    return eventPlace;
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
}