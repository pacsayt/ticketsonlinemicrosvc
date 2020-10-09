package springboot.ticketsonlinemicrosvc.common.entities.ticket;

import springboot.ticketsonlinemicrosvc.common.entities.event.Event;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "ticket")
public class TicketEntity
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

  public TicketEntity()
  {
  }

  public TicketEntity(Long iniId, Integer iniSeatNo, Long iniEventId, Integer iniTicketPrice)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    eventId = iniEventId;
    ticketPrice = iniTicketPrice;
  }

  public TicketEntity( Ticket iniTicket,  Long iniEventId)
  {
    iD = iniTicket.getiD();
    seatNo = iniTicket.getSeatNo();
    ticketPrice = iniTicket.getTicketPrice();
    eventId = iniEventId;
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

    TicketEntity ticketEntity = (TicketEntity) o;

    return Objects.equals(iD, ticketEntity.iD) &&
            Objects.equals(seatNo, ticketEntity.seatNo) &&
            Objects.equals(eventId, ticketEntity.eventId) &&
            Objects.equals(ticketPrice, ticketEntity.ticketPrice);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, seatNo, eventId, ticketPrice);
  }
}