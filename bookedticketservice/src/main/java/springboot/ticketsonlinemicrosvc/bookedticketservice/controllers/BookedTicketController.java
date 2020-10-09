package springboot.ticketsonlinemicrosvc.bookedticketservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTickets;

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
  public BookedTicketEntity getById(@PathVariable Long bookedTicketId)
  {
    BookedTicketEntity bookedTicketEntityFound = bookedTicketService.findById( bookedTicketId).orElseGet( () -> new BookedTicketEntity());

    return bookedTicketEntityFound;
  }

  @PostMapping()
  public ResponseEntity<Optional<BookedTicketEntity>> post(@RequestBody BookedTicketEntity bookedTicketEntity)
  {
    BookedTicketEntity bookedTicketEntityAfterSave = bookedTicketService.save(bookedTicketEntity);

    return ResponseEntity.ok( Optional.of(bookedTicketEntityAfterSave));
  }

  @PutMapping()
  public ResponseEntity<Optional<BookedTicketEntity>> put(@RequestBody BookedTicketEntity bookedTicketEntity)
  {
    BookedTicketEntity bookedTicketEntityAfterSave = bookedTicketService.save(bookedTicketEntity);

    return ResponseEntity.ok( Optional.of(bookedTicketEntityAfterSave));
  }
}
