package springboot.ticketsonlinemicrosvc.common.entities.event;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
// import java.util.Date;

/**
 * This is the object oriented version.
 * Split into EventEntity and EventPlace classes to save to a DB.
 */
public class Event
{
  private Long iD;

  private String name;

  private Timestamp date;

  private EventPlace eventPlace;

  public Event()
  {
  }

  public Event(Long iniID, String iniName, Timestamp iniDate, EventPlace iniEventPlace)
  {
    iD = iniID;
    name = iniName;
    date = iniDate;
    eventPlace = iniEventPlace;
  }

  public Event( EventEntity iniEventEntity, EventPlace iniEventPlace)
  {
    iD = iniEventEntity.getId();
    name = iniEventEntity.getName();
    date = iniEventEntity.getDate();
    eventPlace = iniEventPlace;
  }

  /*
    @Id // pt++ : should this annotation be added here or to the member definition ?
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
  */
  public Long getId() {
    return iD;
  }

  public void setId(Long iniId)
  {
    iD = iniId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String iniName)
  {
    name = iniName;
  }

  public Timestamp getDate()
  {
    return date;
  }

  public void setDate(Timestamp iniDate)
  {
    date = iniDate;
  }

  public EventPlace getEventPlace()
  {
    return eventPlace;
  }

  public void setEventPlace(EventPlace iniEventPlace)
  {
    eventPlace = iniEventPlace;
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

    Event event = (Event) o;

    return Objects.equals(iD, event.iD) &&
            Objects.equals(name, event.name) &&
            Objects.equals(date, event.date) &&
            Objects.equals(eventPlace, event.eventPlace);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, name, date, eventPlace);
  }
}