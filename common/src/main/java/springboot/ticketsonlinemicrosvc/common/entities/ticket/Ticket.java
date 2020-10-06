package springboot.ticketsonlinemicrosvc.common.entities.ticket;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "ticket")
public class Ticket
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, GenerationType.IDENTITY ???
  @Column( name = "ticket_id") // pt++ : just to show it ...
  private Long iD;

  @Column( name = "seat_no", precision = 255, scale = 0) // pt++ : just to show it ...
  private Integer seatNo;

  @Column( name="event_id", nullable=false)
  private Long eventId;

  @Column( name="ticket_price")
  private Integer ticketPrice;

  public Ticket()
  {
  }

  public Ticket(Long iniId, Integer iniSeatNo, Long iniEventId, Integer iniTicketPrice)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    eventId = iniEventId;
    ticketPrice = iniTicketPrice;
  }

  public Long getiD()
  {
    return iD;
  }

  public void setiD(Long iniId)
  {
    iD = iniId;
  }

  public Integer getSeatNo()
  {
    return seatNo;
  }

  public void setSeatNo(Integer iniSeatNo)
  {
    seatNo = iniSeatNo;
  }

  public Long getEventId()
  {
    return eventId;
  }

  public void setEventId(Long iniEventId)
  {
    eventId = iniEventId;
  }

  public Integer getTicketPrice()
  {
    return ticketPrice;
  }

  public void setTicketPrice(Integer iniTicketPrice)
  {
    ticketPrice = iniTicketPrice;
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

    Ticket ticket = (Ticket) o;

    return Objects.equals(iD, ticket.iD) &&
            Objects.equals(seatNo, ticket.seatNo) &&
            Objects.equals(eventId, ticket.eventId) &&
            Objects.equals(ticketPrice, ticket.ticketPrice);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, seatNo, eventId, ticketPrice);
  }
}