package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Tickets;
import springboot.ticketsonlinemicrosvc.ticketservice.services.TicketService;

import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping( path="ticket")
public class TicketController
{
  private static final Logger LOG = LoggerFactory.getLogger( TicketController.class);

  @Autowired
  private TicketService ticketService;

  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
  @Value( "${parameter:default value}")
  private String parameter;

  @Value("${shared_parameter:Config Server is not working. Please check...}")
  private String sharedParameter;

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

    return ResponseEntity.ok( Optional.of( ticketSaved)); // pt++ : could be TicketEntity as well
  }

  @PutMapping
  public ResponseEntity<Optional<Ticket>> put(@RequestBody Ticket ticket)
  {
    Ticket ticketUpdated = ticketService.save( ticket);

    return ResponseEntity.ok( Optional.of( ticketUpdated));
  }

  @GetMapping( path = "/config")
  public String getConfig()
  {
    LOG.info( "TicketController::getConfig() parameter=" + parameter + "\n" + sharedParameter);

    return parameter + "\n" + sharedParameter;
  }
}