package springboot.ticketsonlinemicrosvc.common.entities.event;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
// import java.util.Date;

/**
 * called persistent classes in Hibernate
 *
 * rules of persistent classes (no hard requirements)
 * - default constructor
 * - contain an ID
 * - getXXX()/setXXX() - JavaBean style
 * - central feature of Hibernate, proxies, depends upon the persistent class being either non-final,
 *   or the implementation of an interface that declares all public methods.
 * - no EJB  
 */
@Entity
@Table( name = "event") // , uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "date"})}) // pt++
public class EventEntity
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "")
  @Column(name = "event_id", unique = true)
  private Long iD;

  @Column( name="name", length = 255, nullable = true, unique = false)
  private String name;

  @Column( name="date")
  private Timestamp date;

  @Column( name="event_place_id")
  private Long eventPlaceId;

  public EventEntity()
  {
  }

  @Autowired // pt++ : @Autowired what for ???
  public EventEntity(Long iniID, String iniName, Timestamp iniDate, Long iniEventPlaceId)
  {
    iD = iniID;
    name = iniName;
    date = iniDate;
    eventPlaceId = iniEventPlaceId;
  }

  public EventEntity( Event iniEvent)
  {
    this( iniEvent, iniEvent.getEventPlace().getId());
  }

  public EventEntity( Event iniEvent, Long iniEventPlaceId)
  {
    iD = iniEvent.getId();
    name = iniEvent.getName();
    date = iniEvent.getDate();
    eventPlaceId = iniEventPlaceId;
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

  public Long getEventPlaceId()
  {
    return eventPlaceId;
  }

  public void setEventPlaceId(Long iniEventPlaceId)
  {
    eventPlaceId = iniEventPlaceId;
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

    EventEntity eventEntity = (EventEntity) o;

    return Objects.equals(iD, eventEntity.iD) &&
           Objects.equals(name, eventEntity.name) &&
           Objects.equals(date, eventEntity.date) &&
           Objects.equals(eventPlaceId, eventEntity.eventPlaceId);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, name, date, eventPlaceId);
  }
}
