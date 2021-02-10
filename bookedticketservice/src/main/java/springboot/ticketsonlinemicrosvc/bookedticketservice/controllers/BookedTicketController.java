package springboot.ticketsonlinemicrosvc.bookedticketservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTickets;

import java.util.Optional;

/**
 *  * http://localhost:8014/bookedticket/
 * http://localhost:8014/bookedticket/11
 *
 * http://localhost:8014/eventplace/config : prints out the parameter got from configuration server
 *
 * http://localhost:8014/actuator/refresh/
 *
 * the configuration values are read on the client’s startup ONLY
 * /actuator/refresh : triggers fetching them again <-> @RefreshScope
 *
 * http://localhost:8014/ -> enter : https://localhost:8014/hystrix.stream
 *
 */

@RestController
@RequestMapping( path="bookedticket")
public class BookedTicketController
{
  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
  @Value( "${parameter}")
  private String parameter;

  @Value("${shared_parameter:Config Server is not working. Please check...}")
  private String sharedParameter;

  @Autowired
  private BookedTicketService bookedTicketService;

  @GetMapping
  public BookedTickets getAll()
  {
    return new BookedTickets( bookedTicketService.findAll());
  }

  @GetMapping( path="{bookedTicketId}", produces = "application/json")
  public BookedTicket getById(@PathVariable Long bookedTicketId)
  {
    BookedTicket bookedTicketFound = bookedTicketService.findById( bookedTicketId).orElseGet( () -> new BookedTicket());

    return bookedTicketFound;
  }

  @PostMapping()
  public ResponseEntity<Optional<BookedTicket>> post(@RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketEntityAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of(bookedTicketEntityAfterSave));
  }

  @PutMapping()
  public ResponseEntity<Optional<BookedTicket>> put(@RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketEntityAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of( bookedTicketEntityAfterSave));
  }

  @GetMapping( path = "/config")
  public String getConfig()
  {
    return parameter + "\n" + sharedParameter;
  }
}
