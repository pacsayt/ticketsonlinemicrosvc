package springboot.ticketsonlinemicrosvc.eventservice.ticketsonlinemicrosvc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.Event;
import springboot.ticketsonlinemicrosvc.common.entities.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.Ticket;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class BookedTicketServiceTest extends TestBase
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
    List<BookedTicket> listBookedTickets = bookedTicketService.findAll();

    assertEquals( 2, listBookedTickets.size());
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
//    Event eventToSave = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceSaved);
//
//    Event eventSaved = eventService.save( eventToSave);
//
//    Ticket ticketToSave = new Ticket( 0L, 55, eventSaved, 55);
//
//    Ticket ticketSaved = ticketService.save( ticketToSave);
    EventPlace eventPlaceToSave = new EventPlace( 111L, "Name_55", 110);
    Event eventToSave = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceToSave);
    Ticket ticketToSave = new Ticket( 0L, 55, eventToSave, 55);

    BookedTicket bookedTicketToBeSaved = new BookedTicket( 0L, ticketToSave);

    BookedTicket bookedTicketSaved = bookedTicketService.save( bookedTicketToBeSaved);

    Optional<BookedTicket> optionalBookedTicket = bookedTicketService.findById( bookedTicketSaved.getiD());

    assertTrue( optionalBookedTicket.isPresent());
  }

  @Test
  public void testFindByBookedTicketEvent() throws ParseException
  {
    EventPlace eventPlaceSearched = new EventPlace( 111L, "Name_55", 110); // (Long iniID, String iniName, Integer iniNoOfSeats)

    Event eventSearchCriteria = new Event( 22L, "EventName_22", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceSearched);

    List<BookedTicket> bookedTicketsForEvent = bookedTicketService.findByBookedTicketEvent( eventSearchCriteria);

    assertEquals( 22L, bookedTicketsForEvent.get( 0).getiD());
  }

  @Test
  public void testFindAvailableTickets() throws ParseException
  {
    Integer availableTickets  = bookedTicketService.findAvailableTickets( "EventName_22", stringToDate("2020-09-03 11:32:41.00")); // pt++ : some useful functionality to be implemented here

    assertEquals( 21, availableTickets);
  }
}