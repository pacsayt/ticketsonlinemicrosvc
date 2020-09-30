package springboot.ticketsonlinemicrosvc.common.entities;

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

  @ManyToOne
  @JoinColumn( name="event_id", nullable=false)
  private Event event;

  @Column( name="ticket_price")
  private Integer ticketPrice;

  public Ticket()
  {
  }

  public Ticket(Long iniId, Integer iniSeatNo, Event iniEvent, Integer iniTicketPrice)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    event = iniEvent;
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

  public Event getEvent()
  {
    return event;
  }

  public void setEvent(Event iniEvent)
  {
    event = iniEvent;
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
            Objects.equals(event, ticket.event) &&
            Objects.equals(ticketPrice, ticket.ticketPrice);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, seatNo, event, ticketPrice);
  }
}