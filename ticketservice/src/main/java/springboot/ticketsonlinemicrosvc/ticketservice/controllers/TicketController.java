package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.Tickets;
import springboot.ticketsonlinemicrosvc.ticketservice.services.TicketService;

import java.util.Optional;

@RestController
@RequestMapping( path="ticket")
public class TicketController
{
  @Autowired
  private TicketService ticketService;

  @GetMapping
  public Tickets getAll()
  {
    return new Tickets( ticketService.findAll());
  }

  @GetMapping( path="{ticketId}", produces="application/json")
  public Ticket getById(@PathVariable Long ticketId)
  {
    Ticket ticketFound = ticketService.findById( ticketId).orElseGet( () -> new Ticket());

    return ticketFound;
  }

  @PostMapping
  public ResponseEntity<Object> post( @RequestBody Ticket ticket)
  {
    Ticket ticketSaved = ticketService.save( ticket);

    return ResponseEntity.ok( Optional.of( ticketSaved)); // pt++ : could be Ticket as well
  }

  @PutMapping
  public ResponseEntity<Optional<Ticket>> put( @RequestBody Ticket ticket)
  {
    Ticket ticketUpdated = ticketService.save( ticket);

    return ResponseEntity.ok( Optional.of( ticketUpdated));
  }
}