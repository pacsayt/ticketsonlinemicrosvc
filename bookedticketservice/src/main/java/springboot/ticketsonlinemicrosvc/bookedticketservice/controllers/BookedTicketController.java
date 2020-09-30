package springboot.ticketsonlinemicrosvc.bookedticketservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.BookedTickets;

import java.util.Optional;

@RestController
@RequestMapping( path="bookedticket")
public class BookedTicketController
{
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
  public ResponseEntity<Optional<BookedTicket>> post( @RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of( bookedTicketAfterSave));
  }

  @PutMapping()
  public ResponseEntity<Optional<BookedTicket>> put( @RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of( bookedTicketAfterSave));
  }
}
