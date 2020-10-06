package springboot.ticketsonlinemicrosvc.bookedticketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonlinemicrosvc.bookedticketservice.repositories.BookedTicketRepository;
import springboot.ticketsonlinemicrosvc.common.entities.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.Event;

import java.sql.Timestamp;
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
//  @Autowired
//  private EventRepository eventRepository; pt++ : must call event microservice from a higher level

//  @Autowired
//  private TicketService ticketService; ???

  @Autowired
  private BookedTicketRepository bookedTicketRepository;

  public Long count()
  {
    return bookedTicketRepository.count();
  }

  public BookedTicket save( BookedTicket bookedTicketToSave)
  {
    return bookedTicketRepository.save( bookedTicketToSave);
  }

  public Optional<BookedTicket> findById( Long iD)
  {
    Optional<BookedTicket> bookedTicketOptional = bookedTicketRepository.findById( iD);

    return bookedTicketOptional;
  }

  public List<BookedTicket> findAll()
  {
    return bookedTicketRepository.findAll();
  }

  public List<BookedTicket> findByBookedTicketEvent( Event event)
  {
    return Collections.emptyList(); // bookedTicketRepository.findByBookedTicketEvent( event);
  }

  public Integer findAvailableTickets( String name, Timestamp date) // pt++ : must be updated because of microservices
  {
    Integer noOfFreSeatsForTheEvent = 0;
/*
    Optional<Event> optionalEventOnADay = Optional.empty(); // eventRepository.findByNameAndDate( name, date); pt++ : must call event microservice from a higher level

    if ( optionalEventOnADay.isPresent() )
    {
// ??? List<Ticket> listTicketsForTheEvent = ticketService.findByEvent( optionalEventOnADay.get());
      Event matchingEvent = optionalEventOnADay.get();
      List<BookedTicket> bookedTicketsForTheEvent = bookedTicketRepository.findByBookedTicketEvent( matchingEvent);

      noOfFreSeatsForTheEvent = matchingEvent.getEventPlace().getNoOfSeats() - bookedTicketsForTheEvent.size();
    }
*/
    return noOfFreSeatsForTheEvent;
  }

  public void deleteById( Long iD)
  {
    bookedTicketRepository.deleteById( iD);
  }

  // pt++ : what is something similar
  public void delete( BookedTicket bookedTicketToBeDeleted)
  {
    bookedTicketRepository.delete( bookedTicketToBeDeleted);
  }
}
