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

@RestController
@RequestMapping( path="bookedticket")
public class BookedTicketController
{
  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
  @Value( "${parameter}")
  private String parameter;

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
    return parameter;
  }
}
