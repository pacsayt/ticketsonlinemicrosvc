package springboot.ticketsonlinemicrosvc.common.entities.bookedticket;

import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import java.util.Objects;

public class BookedTicket
{
  private Long iD;

  private TicketEntity bookedTicketEntity;

  public BookedTicket()
  {
  }

  public BookedTicket( Long iniId, TicketEntity iniBookedTicketEntity)
  {
    iD = iniId;
    bookedTicketEntity = iniBookedTicketEntity;
  }

  public TicketEntity getBookedTicket()
  {
    return bookedTicketEntity;
  }

  public void setBookedTickets(TicketEntity iniBookedTicketEntity)
  {
    bookedTicketEntity = iniBookedTicketEntity;
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
            Objects.equals(bookedTicketEntity, that.bookedTicketEntity);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, bookedTicketEntity);
  }
}