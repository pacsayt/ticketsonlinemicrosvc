package springboot.ticketsonlinemicrosvc.ticketservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;
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
  private static final Logger LOG = LoggerFactory.getLogger( TicketService.class);

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
    Event eventSaved = eventServiceAccess.post( ticketToBeSaved.getEvent());

    TicketEntity ticketEntityToBeSaved = new TicketEntity( ticketToBeSaved, eventSaved.getId());

    TicketEntity savedTicketEntity = ticketRepository.save( ticketEntityToBeSaved);

    return new Ticket( savedTicketEntity, ticketToBeSaved.getEvent());
  }

  public void update( Ticket ticketToBeSaved)
  {
    eventServiceAccess.put( ticketToBeSaved.getEvent());

    TicketEntity ticketEntityToBeSaved = new TicketEntity( ticketToBeSaved, ticketToBeSaved.getEvent().getId());

    TicketEntity savedTicketEntity = ticketRepository.save( ticketEntityToBeSaved);
  }

  public List<Ticket> findAll()
  {
    List<Ticket> allTickets = new ArrayList<>();

    Events events = eventServiceAccess.getAll();

    for ( TicketEntity ticketEntity: ticketRepository.findAll() )
    {
      for ( Event event : events.getEvents() )
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
    LOG.info( "TicketService::findById( " + iD + ") ++++++++++++++++++++++++++++++++++++++++++++");

    Optional<TicketEntity> ticketEntityFoundOptional = ticketRepository.findById( iD);

    if ( ticketEntityFoundOptional.isPresent() )
    {
      Event eventFound = eventServiceAccess.getById( ticketEntityFoundOptional.get().getEventId());

      LOG.info( "TicketService::findById( " + iD + ") -> " + eventFound + " ++++++++++++++++++++++++++++++++++++++++++++");

      return Optional.of( new Ticket( ticketEntityFoundOptional.get(), eventFound));
    }

    LOG.info( "TicketService::findById( ) -> Optional.empty() ++++++++++++++++++++++++++++++++++++++++++++");

    return Optional.empty();
  }

  public List<Ticket> findByEventId(Long eventId)
  {
    List<Ticket> allTickets = new ArrayList<>();

    List<TicketEntity> ticketsFounfByEventId = ticketRepository.findByEventId( eventId);

    for ( TicketEntity ticketEntity : ticketsFounfByEventId )
    {
      Event eventFound = eventServiceAccess.getById( eventId);

      LOG.info( "TicketService::findByEventId( " + eventId + ") -> " + eventFound + " ++++++++++++++++++++++++++++++++++++++++++++");

      allTickets.add( new Ticket( ticketEntity, eventFound));
    }

    return allTickets;
  }

  public void delete( Ticket ticketToBeDeleted)
  {
    ticketRepository.delete( new TicketEntity( ticketToBeDeleted, ticketToBeDeleted.getEvent().getId()));
  }

  public Ticket getOne( Long iD)
  {
    Optional<TicketEntity> ticketEntityFoundOptional = ticketRepository.findById( iD);

    if ( ticketEntityFoundOptional.isPresent() )
    {
      Event eventFound  = eventServiceAccess.getById( ticketEntityFoundOptional.get().getEventId());

      LOG.info( "TicketService::getOne( " + iD + ") -> " + eventFound + " ++++++++++++++++++++++++++++++++++++++++++++");

      return new Ticket( ticketEntityFoundOptional.get(), eventFound);
    }

    return null;
  }

  // pt++ : used in test(s) only
  public EntityManager getEntityManager()
  {
    return entityManager;
  }
}