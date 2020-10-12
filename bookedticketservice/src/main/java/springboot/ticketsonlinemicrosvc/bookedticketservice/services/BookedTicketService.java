package springboot.ticketsonlinemicrosvc.bookedticketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.bookedticketservice.repositories.BookedTicketRepository;
import springboot.ticketsonlinemicrosvc.bookedticketservice.restaccess.TicketServiceAccess;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Tickets;
import springboot.ticketsonlinemicrosvc.ticketservice.restaccess.EventServiceAccess;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * pt++ : Stereotypes : @Component, @Service, @Repository, @Controller Difference
 * Spring @Component, @Service, @Repository, @Controller Difference
 */
@Service
public class BookedTicketService
{
  @Autowired
  private TicketServiceAccess ticketServiceAccess;

  @Autowired
  private EventServiceAccess eventServiceAccess;

  @Autowired
  private BookedTicketRepository bookedTicketRepository;

  public Long count()
  {
    return bookedTicketRepository.count();
  }

  public BookedTicket save( BookedTicket bookedTicketToSave)
  {
    Ticket savedTicket = ticketServiceAccess.post( bookedTicketToSave.getTicket());

    BookedTicketEntity bookedTicketEntityToSave = new BookedTicketEntity( savedTicket.getiD());

    BookedTicketEntity bookedTicketEntitySaved = bookedTicketRepository.save( bookedTicketEntityToSave);

    BookedTicket bookedTicketSaved = new BookedTicket( bookedTicketEntitySaved.getiD(), savedTicket);

    return bookedTicketSaved;
  }

  public Optional<BookedTicket> findById(Long iD)
  {
    Optional<BookedTicketEntity> bookedTicketOptional = bookedTicketRepository.findById( iD);

    if ( bookedTicketOptional.isPresent() )
    {
      BookedTicketEntity bookedTicketEntitytFound = bookedTicketOptional.get();

      Ticket ticketFound = ticketServiceAccess.getById( bookedTicketEntitytFound.getTicketId());

      return Optional.of( new BookedTicket( bookedTicketEntitytFound.getiD(), ticketFound));
    }

    return Optional.empty();
  }

  public List<BookedTicket> findAll()
  {
    List<BookedTicket> bookedTicketList = new ArrayList<>();

    Tickets tickets = ticketServiceAccess.getAll();

    List<Ticket> ticketList = ticketServiceAccess.getAll().getTickets();

    for ( BookedTicketEntity bookedTicketEntity : bookedTicketRepository.findAll() )
    {
      for ( Ticket ticket : ticketList )
      {
        if ( bookedTicketEntity.getTicketId().equals( ticket.getiD()) )
        {
          bookedTicketList.add( new BookedTicket( bookedTicketEntity.getiD(), ticket));
        }
      }
    }

    return bookedTicketList;
  }

  public List<BookedTicket> findByBookedTicketEvent( Event eventEntity)
  {
//    Mono<Event> eventMono = eventServiceAccess.getById( eventEntity.getId());
//
//    Flux<Ticket> ticketFlux = ticketServiceAccess.getByEventId( eventEntity.getId()) pt++ : TODO : this should be implemented
    return Collections.emptyList(); // bookedTicketRepository.findByBookedTicketEvent( eventEntity);
  }

  public Integer findAvailableTickets( String eventName, Timestamp date)
  {
    Integer noOfFreSeatsForTheEvent = 0;

//    eventServiceAccess.getByNameAndDate( name, date);  pt++ : TODO : this should be implemented
//    Flux<Ticket> ticketFlux = ticketServiceAccess.getByEventId( eventEntity.getId()) pt++ : TODO : this should be implemented

    return noOfFreSeatsForTheEvent;
  }

  public void deleteById( Long iD)
  {
    bookedTicketRepository.deleteById( iD);
  }

  // pt++ : what is something similar
  public void delete( BookedTicket bookedTicketToBeDeleted)
  {
    bookedTicketRepository.delete( new BookedTicketEntity( bookedTicketToBeDeleted.getiD(), bookedTicketToBeDeleted.getTicket().getiD()));
  }
}
