package springboot.ticketsonlinemicrosvc.eventservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlaces;

import java.util.Optional;

/**
 * https://www.baeldung.com/spring-cloud-netflix-eureka
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * A good method to create such Feign Clients is to create interfaces with @RequestMapping annotated
 * methods and put them into a separate module. This way they can be shared between server and client.
 * On server-side, you can implement them as @Controller, and on client-side, they can be extended and
 * annotated as @FeignClient.
 */

@FeignClient( name="eventplaceservice", url = "http://localhost:8011/eventplace")
public interface EventPlaceControllerInterface
{
  @GetMapping()
  EventPlaces getAll();

  @GetMapping( path="/{eventPlaceId}", produces = "application/json")
  EventPlace getById(@PathVariable Long eventPlaceId);

  @PostMapping() // pt++ : POST - INSERT
  ResponseEntity<Object> post(@RequestBody EventPlace eventPlace);

  @PutMapping() // pt++ : PUT - update
  ResponseEntity<Optional<EventPlace>> put(@RequestBody EventPlace eventPlace);
}