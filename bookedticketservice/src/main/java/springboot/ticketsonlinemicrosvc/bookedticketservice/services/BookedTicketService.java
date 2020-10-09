package springboot.ticketsonlinemicrosvc.bookedticketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonlinemicrosvc.bookedticketservice.repositories.BookedTicketRepository;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;

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

  public BookedTicketEntity save(BookedTicketEntity bookedTicketEntityToSave)
  {
    return bookedTicketRepository.save(bookedTicketEntityToSave);
  }

  public Optional<BookedTicketEntity> findById(Long iD)
  {
    Optional<BookedTicketEntity> bookedTicketOptional = bookedTicketRepository.findById( iD);

    return bookedTicketOptional;
  }

  public List<BookedTicketEntity> findAll()
  {
    return bookedTicketRepository.findAll();
  }

  public List<BookedTicketEntity> findByBookedTicketEvent(EventEntity eventEntity)
  {
    return Collections.emptyList(); // bookedTicketRepository.findByBookedTicketEvent( eventEntity);
  }

  public Integer findAvailableTickets( String name, Timestamp date) // pt++ : must be updated because of microservices
  {
    Integer noOfFreSeatsForTheEvent = 0;
/*
    Optional<EventEntity> optionalEventOnADay = Optional.empty(); // eventRepository.findByNameAndDate( name, date); pt++ : must call event microservice from a higher level

    if ( optionalEventOnADay.isPresent() )
    {
// ??? List<TicketEntity> listTicketsForTheEvent = ticketService.findByEvent( optionalEventOnADay.get());
      EventEntity matchingEvent = optionalEventOnADay.get();
      List<BookedTicketEntity> bookedTicketsForTheEvent = bookedTicketRepository.findByBookedTicketEvent( matchingEvent);

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
  public void delete( BookedTicketEntity bookedTicketEntityToBeDeleted)
  {
    bookedTicketRepository.delete(bookedTicketEntityToBeDeleted);
  }
}
