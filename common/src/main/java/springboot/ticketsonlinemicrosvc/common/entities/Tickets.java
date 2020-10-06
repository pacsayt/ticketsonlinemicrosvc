package springboot.ticketsonlinemicrosvc.common.entities;

import java.util.Collections;
import java.util.List;

public class Tickets
{
  private List<Ticket> tickets;

  public Tickets()
  {
    tickets = Collections.emptyList();
  }

  public Tickets( List<Ticket> iniTickets)
  {
    tickets = iniTickets;
  }

  public List<Ticket> getTickets()
  {
    return tickets;
  }

  public void setTickets( List<Ticket> iniTickets)
  {
    tickets = iniTickets;
  }
}
