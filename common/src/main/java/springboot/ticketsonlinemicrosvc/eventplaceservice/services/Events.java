package springboot.ticketsonlinemicrosvc.eventplaceservice.services;

import java.util.Collections;
import java.util.List;

public class Events
{
  private List<Event> events;

  public Events()
  {
    events = Collections.emptyList();
  }

  public Events( List<Event> iniEvents)
  {
    events = iniEvents;
  }

  public List<Event> getEvents()
  {
    return events;
  }

  public void setEvents( List<Event> iniEvents)
  {
    events = iniEvents;
  }
}
