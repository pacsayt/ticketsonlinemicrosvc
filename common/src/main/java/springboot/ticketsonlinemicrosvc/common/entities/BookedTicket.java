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

  @OneToOne( targetEntity = Ticket.class) // pt++ : fetch = FetchType.EAGER - default value
//  @Column(name = "booked_ticket")
  private Ticket bookedTicket;

  public BookedTicket()
  {
  }

  public BookedTicket( Long iniId, Ticket iniBookedTicket)
  {
    iD = iniId;
    bookedTicket = iniBookedTicket;
  }

  public Ticket getBookedTicket()
  {
    return bookedTicket;
  }

  public void setBookedTickets(Ticket iniBookedTicket)
  {
    bookedTicket = iniBookedTicket;
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
            Objects.equals(bookedTicket, that.bookedTicket);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, bookedTicket);
  }
}