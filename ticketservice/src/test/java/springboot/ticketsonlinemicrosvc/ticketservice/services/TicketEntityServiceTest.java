package springboot.ticketsonlinemicrosvc.ticketservice.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName( "TicketEntity test cases")
public class TicketEntityServiceTest
{
  @Autowired
  private TicketService ticketService;

  @Test
  public void testFindExistingTicket()
  {
    Optional<Ticket> optionalTicket = ticketService.findById( 11L);

    assertTrue( optionalTicket.isPresent());
  }

  // pt++ : in import.sql there are 6 tickets inserted, here I'll get 5
  // pt++ : test cases influence each other
  @Test
  public void testFindAllTickets()
  {
    List<Ticket> optionalTicketEntity = ticketService.findAll();

    assertEquals( 5, optionalTicketEntity.size()); // pt++ : if execution order of tc-s changes might get 6
  }

  @Test
  public void testDeleteExistingTicket()
  {
    Long ticketsCountBefore = ticketService.count();

//    TicketEntity ticketEntityTobeDeleted = new TicketEntity( 33L, 0, null, 0); pt++ : won't delete as this occours in BookedTickets
    Ticket ticketTobeDeleted = new Ticket( 13L, 0, null, 0);

    ticketService.delete( ticketTobeDeleted);

    Long ticketsCountAfter = ticketService.count();

    assertTrue( ticketsCountBefore > ticketsCountAfter);
  }

  @Test
  public void testEntityManagerAvailable() // pt++ :
  {
    // pt++ : there is such thing like :
// pt++ : EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
// pt++ : EntityManager entitymanager = emfactory.createEntityManager();

    EntityManager entityManager = ticketService.getEntityManager();
    Query query = entityManager.createQuery( "Select t from TicketEntity t");

    // pt++ : query.getResultStream() / getFirstResult()
    List<TicketEntity> ticketEntityResultList = (List<TicketEntity>)query.getResultList();

    assertEquals( 6, ticketEntityResultList.size());
  }

}