package springboot.ticketsonlinemicrosvc.ticketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;
import springboot.ticketsonlinemicrosvc.ticketservice.repositories.TicketRepository;
import springboot.ticketsonlinemicrosvc.ticketservice.restaccess.EventServiceAccess;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * pt++ : https://www.baeldung.com/spring-component-repository-service
 *
 * @Component is a generic stereotype for any Spring-managed component
 * @Service annotates classes at the service layer
 * @Repository annotates classes at the persistence layer, which will act as a database repository
 *
 * Guide to the Hibernate EntityManager
 * https://www.baeldung.com/hibernate-entitymanager
 * @PersistenceContext
 */

@Service
public class TicketService
{
  @Autowired // pt++ : @PersistenceContext( type = ...)
             // PersistenceContextType.TRANSACTION - EntityManager to use the transaction persistence context
             // PersistenceContextType.EXTENDED -
             // injector -> the container is in charge of beginning the transaction, as well as committing or rolling
             //             it back
  private EntityManager entityManager;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private EventServiceAccess eventServiceAccess;

  public Long count()
  {
    return ticketRepository.count();
  }

  public Ticket save( Ticket ticketToBeSaved)
  {
    Mono<Event> eventSavedMono = eventServiceAccess.put( ticketToBeSaved.getEvent());

    Event eventSaved = eventSavedMono.block();

    TicketEntity ticketEntityToBeSaved = new TicketEntity( ticketToBeSaved, eventSaved.getId());

    TicketEntity savedTicketEntity = ticketRepository.save( ticketEntityToBeSaved);

    return new Ticket( savedTicketEntity, eventSaved);
  }

  public List<Ticket> findAll()
  {
    List<Ticket> allTickets = new ArrayList<>();

    Flux<Event> allEventFlux = eventServiceAccess.getAll();

    for ( TicketEntity ticketEntity: ticketRepository.findAll() )
    {
      for ( Event event : allEventFlux.toIterable() )
      {
        if ( ticketEntity.getEventId().equals( event.getId()) )
        {
          allTickets.add( new Ticket( ticketEntity, event));

          break;
        }
      }
    }

    return allTickets;
  }

  public Optional<Ticket> findById(Long iD)
  {
    Optional<TicketEntity> ticketEntityFoundOptional = ticketRepository.findById( iD);

    if ( ticketEntityFoundOptional.isPresent() )
    {
      Mono<Event> eventFoundMono = eventServiceAccess.getById( ticketEntityFoundOptional.get().getEventId());

      return Optional.of( new Ticket( ticketEntityFoundOptional.get(), eventFoundMono.block()));
    }

    return Optional.empty();
  }

  public List<Ticket> findByEventId(Long eventId)
  {
    List<Ticket> allTickets = new ArrayList<>();

    List<TicketEntity> ticketsFounfByEventId = ticketRepository.findByEventId( eventId);

    for ( TicketEntity ticketEntity : ticketsFounfByEventId )
    {
      Mono<Event> eventFoundMono = eventServiceAccess.getById( eventId);
      Event eventFound = eventFoundMono.block();

      allTickets.add( new Ticket( ticketEntity, eventFound));
    }

    return allTickets;
  }

  public void delete( Ticket ticketEntityToBeDeleted)
  {
    ticketRepository.delete( new TicketEntity( ticketEntityToBeDeleted, ticketEntityToBeDeleted.getEvent().getId()));
  }

  public Ticket getOne( Long iD)
  {
    Optional<TicketEntity> ticketEntityFoundOptional = ticketRepository.findById( iD);

    if ( ticketEntityFoundOptional.isPresent() )
    {
      Mono<Event> eventFoundMono = eventServiceAccess.getById( ticketEntityFoundOptional.get().getEventId());

      return new Ticket( ticketEntityFoundOptional.get(), eventFoundMono.block());
    }

    return null;
  }

  // pt++ : used in test(s) only
  public EntityManager getEntityManager()
  {
    return entityManager;
  }
}