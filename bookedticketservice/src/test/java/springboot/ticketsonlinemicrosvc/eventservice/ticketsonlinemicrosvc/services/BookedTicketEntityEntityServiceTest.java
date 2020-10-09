package springboot.ticketsonlinemicrosvc.eventservice.ticketsonlinemicrosvc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class BookedTicketEntityEntityServiceTest extends TestBase
{
//  @Autowired
//  private EventPlaceService eventPlaceService; pt++ : will be done over microservicer anyway ...
//
//  @Autowired
//  private EventService eventService;
//
//  @Autowired
//  private TicketService ticketService;

  @Autowired
  private BookedTicketService bookedTicketService;

  @Test
  public void testAmountAllBookedTickets()
  {
    Long amountBookedTickets = bookedTicketService.count();

    assertEquals( 2, amountBookedTickets);
  }

  @Test
  public void testfindAll()
  {
    List<BookedTicketEntity> listBookedTicketEntities = bookedTicketService.findAll();

    assertEquals( 2, listBookedTicketEntities.size());
  }

  @Test
  public void testSaveBookedTicket() throws ParseException
  {
//    EventPlace eventPlaceToSave;                                   pt++ : will be done over microservicer anyway ...
//    EventPlace eventPlaceSaved;
//
//    eventPlaceToSave = new EventPlace( 111L, "Name_55", 110); // (Long iniID, String iniName, Integer iniNoOfSeats)
//    eventPlaceSaved = eventPlaceService.save( eventPlaceToSave);
//
//    assertTrue( 111L != eventPlaceSaved.getId());
//
//    EventEntity eventToSave = new EventEntity( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceSaved);
//
//    EventEntity eventSaved = eventService.save( eventToSave);
//
//    TicketEntity ticketToSave = new TicketEntity( 0L, 55, eventSaved, 55);
//
//    TicketEntity ticketSaved = ticketService.save( ticketToSave);
//    EventPlace eventPlaceToSave = new EventPlace( 111L, "Name_55", 110);
//    EventEntity eventToSave = new EventEntity( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), 111L); // pt++ : bcause of microservice
//    TicketEntity ticketToSave = new TicketEntity( 0L, 55, 55L, 55);

    BookedTicketEntity bookedTicketEntityToBeSaved = new BookedTicketEntity( 0L, 55L);

    BookedTicketEntity bookedTicketEntitySaved = bookedTicketService.save(bookedTicketEntityToBeSaved);

    Optional<BookedTicketEntity> optionalBookedTicket = bookedTicketService.findById( bookedTicketEntitySaved.getiD());

    assertTrue( optionalBookedTicket.isPresent());
  }

  @Test
  public void testFindByBookedTicketEvent() throws ParseException
  {
//    EventPlace eventPlaceSearched = new EventPlace( 111L, "Name_55", 110); // (Long iniID, String iniName, Integer iniNoOfSeats) pt++ : microservices

    EventEntity eventEntitySearchCriteria = new EventEntity( 22L, "EventName_22", stringToDate( "2020-09-03 11:32:41.00"), 22L);

    List<BookedTicketEntity> bookedTicketsForEventEntity = bookedTicketService.findByBookedTicketEvent(eventEntitySearchCriteria);

    assertEquals( 22L, bookedTicketsForEventEntity.get( 0).getiD());
  }

  @Test
  public void testFindAvailableTickets() throws ParseException
  {
    Integer availableTickets  = bookedTicketService.findAvailableTickets( "EventName_22", stringToDate("2020-09-03 11:32:41.00")); // pt++ : some useful functionality to be implemented here

    assertEquals( 21, availableTickets);
  }
}