package springboot.ticketsonlinemicrosvc.common.entities.bookedticket;

import java.util.Collections;
import java.util.List;

public class BookedTickets
{
  private List<BookedTicket> bookedTickets;

  public BookedTickets()
  {
    bookedTickets = Collections.emptyList();
  }

  public BookedTickets( List<BookedTicket> iniBookedTickets)
  {
    bookedTickets = iniBookedTickets;
  }

  public List<BookedTicket> getBookedTicket()
  {
    return bookedTickets;
  }

  public void setBookedTicket( List<BookedTicket> iniBookedTickets)
  {
    bookedTickets = iniBookedTickets;
  }
}
