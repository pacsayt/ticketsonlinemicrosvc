package springboot.ticketsonlinemicrosvc.common.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name="bookedticket")
public class BookedTicket
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, just to show them ...
  @Column(name = "bookedticket_id") // pt++ : just to show it ...
  private Long iD;

  // pt++ : fetch = FetchType.EAGER - default value
  @Column(name = "ticket_id")
  private Long ticketId;

  public BookedTicket()
  {
  }

  public BookedTicket( Long iniId, Long iniTicketId)
  {
    iD = iniId;
    ticketId = iniTicketId;
  }

  public Long getTicketId()
  {
    return ticketId;
  }

  public void seTicketId(Long iniTicketId)
  {
    ticketId = iniTicketId;
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
            Objects.equals(ticketId, that.ticketId);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, ticketId);
  }
}