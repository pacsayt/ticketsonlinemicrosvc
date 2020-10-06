package springboot.ticketsonlinemicrosvc.common.entities;

import java.util.List;

/**
 * pt++ : A wrapper class must be used if one wants to transfer more than one object.
 * pt++ : if this is true for RestTemplate ONLY ???
 *
 * Get and Post Lists of Objects with RestTemplate
 * https://www.baeldung.com/spring-rest-template-list
 *
 */
public class EventPlaces
{
  private List<EventPlace> eventPlaces;

  public EventPlaces()
  {
  }

  public EventPlaces( List<EventPlace> iniEventPlaces)
  {
    eventPlaces = iniEventPlaces;
  }

  public List<EventPlace> getEventPlaces()
  {
    return eventPlaces;
  }

  public void setEventPlace( List<EventPlace> iniEventPlaces)
  {
    eventPlaces = iniEventPlaces;
  }
}
