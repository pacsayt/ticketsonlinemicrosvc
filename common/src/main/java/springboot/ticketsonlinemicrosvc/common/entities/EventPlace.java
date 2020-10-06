package springboot.ticketsonlinemicrosvc.common.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * H2 in-memory database. Table not found
 * https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
 */
@Entity
@Table(name = "event_place")
public class EventPlace
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "event_place_id", unique = true)
  private Long iD;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "no_of_seats", precision = 255, scale = 0)
  private Integer noOfSeats;

  public EventPlace()
  {
  }

  public EventPlace(Long iniID, String iniName, Integer iniNoOfSeats)
  {
    iD = iniID;
    name = iniName;
    noOfSeats = iniNoOfSeats;
  }

  public Long getId()
  {
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

  public Integer getNoOfSeats()
  {
    return noOfSeats;
  }

  public void setNoOfSeats(Integer iniNoOfSeats)
  {
    noOfSeats = iniNoOfSeats;
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

    EventPlace that = (EventPlace) o;

    return Objects.equals(iD, that.iD) &&
           Objects.equals(name, that.name) &&
           Objects.equals(noOfSeats, that.noOfSeats);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(iD, name, noOfSeats);
  }

  @Override
  public String toString()
  {
    return "EventPlace{ iD=" + iD + ", name='" + name + '\'' + ", noOfSeats=" + noOfSeats + '}';
  }
}
