package springboot.ticketsonlinemicrosvc.common.entities.bookedticket;

import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;

import java.util.Objects;

public class BookedTicket
{
  private Long iD;

  private Ticket ticket;

  public BookedTicket()
  {
  }

  public BookedTicket( Long iniId, Ticket iniTicket)
  {
    iD = iniId;
    ticket = iniTicket;
  }

  public Ticket getTicket()
  {
    return ticket;
  }

  public void setBookedTickets( Ticket iniTicket)
  {
    ticket = iniTicket;
  }

  public Long getiD()
  {
    return iD;
  }

  public void setiD( Long iniId)
  {
    iD = iniId;
  }

  @Override
  public boolean equals(Object o)
  {
    if ( this == o )
    {
      return true;
    }

    if ( o == null || getClass() != o.getClass() )
    {
      return false;
    }

    BookedTicket that = (BookedTicket) o;
    return Objects.equals(iD, that.iD) &&
            Objects.equals(ticket, that.ticket);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, ticket);
  }
}