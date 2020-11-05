package springboot.ticketsonlinemicrosvc.ticketservice.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;
import springboot.ticketsonlinemicrosvc.ticketservice.restaccess.EventServiceAccess;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Mockito Verify Cookbook
 * https://www.baeldung.com/mockito-verify
 */

@SpringBootTest
// @DataJpaTest : pt++ : TicketService is not available when using this
@ExtendWith( SpringExtension.class)
@DisplayName( "TicketEntity test cases")
public class TicketEntityServiceTest extends TestBase
{
  @MockBean // pt++ : add mock objects to the Spring application context.
            //        The mock will replace any existing bean of the same type in the application context.
  private EventServiceAccess mockEventServiceAccess;

  @Autowired
  private TicketService ticketService;

  @Test
  @Order(1)
  public void testFindExistingTicket() throws ParseException
  {
    when( mockEventServiceAccess.getById( 1L)).thenReturn( new Event(  1L, "EventName_11", stringToDate( "2020-09-03 11:32:41"), null));
    Optional<Ticket> optionalTicket = ticketService.findById( 11L);

    assertTrue( optionalTicket.isPresent());

    verify( mockEventServiceAccess, times(1)).getById( 1L);
  }

  // pt++ : in import.sql there are 6 tickets inserted, here I'll get 5
  // pt++ : test cases influence each other
  @Test
  @Order(2)
  public void testFindAllTickets() throws ParseException
  {
    when( mockEventServiceAccess.getAll()).thenReturn( new Events( List.of( new Event(  1L, "EventName_11", stringToDate( "2020-09-03 11:32:41"), null),
                                                                            new Event(  2L, "EventName_11", stringToDate( "2020-09-03 11:32:41"), null),
                                                                            new Event(  3L, "EventName_11", stringToDate( "2020-09-03 11:32:41"), null),
                                                                            new Event(  4L, "EventName_11", stringToDate( "2020-09-03 11:32:41"), null))));

    List<Ticket> optionalTicketEntity = ticketService.findAll();

    assertEquals( 5, optionalTicketEntity.size()); // pt++ : if execution order of tc-s changes might get 6 ->   @Order(...) seems to have no effect
  }

//  @Disabled // pt++ : == @Ignore
  @Test
  @Order(3)
  public void testDeleteExistingTicket()
  {
    Long ticketsCountBefore = ticketService.count();

    Event event = new Event( 33L, "EventName_33", null, null);
    Ticket ticketTobeDeleted = new Ticket( 13L, 0, event, 0);

    ticketService.delete( ticketTobeDeleted);

    Long ticketsCountAfter = ticketService.count();

    assertTrue( ticketsCountBefore > ticketsCountAfter);
  }

  @Test
  @Order(4)
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