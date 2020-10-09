package springboot.ticketsonlinemicrosvc.common.entities.ticket;

import springboot.ticketsonlinemicrosvc.common.entities.event.Event;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class Ticket
{
  private Long iD;

  private Integer seatNo;

  private Event event;

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

  public Ticket( TicketEntity iniTicketEntity, Event iniEvent)
  {
    iD = iniTicketEntity.getiD();
    seatNo = iniTicketEntity.getSeatNo();
    event = iniEvent;
    ticketPrice = iniTicketEntity.getTicketPrice();
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