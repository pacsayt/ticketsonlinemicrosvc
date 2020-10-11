package springboot.ticketsonlinemicrosvc.bookedticketservice.restaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Tickets;

@Component
public class TicketServiceAccess
{
  @Autowired
  private WebClient webClient;

  public Tickets  getAll()
  {
    return webClient.get()
                    .uri( "http://ticketservice/ticket")
                    .retrieve()
                    .bodyToMono( Tickets.class)
                    .block();
  }

  public Ticket getById( Long ticketId)
  {
    return webClient.get()
                    .uri( "http://ticketservice/")
                    .retrieve()
                    .bodyToMono( Ticket.class)
                    .block();
  }

  public Ticket post( Ticket ticketToSave)
  {
    return webClient.post()
                    .uri( "http://ticketservice/ticket")
                    .body( ticketToSave, Ticket.class)
                    .retrieve()
                    .bodyToMono( Ticket.class)
                    .block();
  }

  public Ticket put( Ticket ticketToSave)
  {
    return webClient.put()
                    .uri( "http://ticketservice/ticket")
                    .body( ticketToSave, Ticket.class)
                    .retrieve()
                    .bodyToMono( Ticket.class)
                    .block();
  }
}
