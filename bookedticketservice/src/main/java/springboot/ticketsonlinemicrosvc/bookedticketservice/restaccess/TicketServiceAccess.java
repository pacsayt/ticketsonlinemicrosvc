package springboot.ticketsonlinemicrosvc.bookedticketservice.restaccess;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;

@Component
public class TicketServiceAccess
{
  private WebClient webClient;

  // pt++ : TODO : to be continued from here ...
  Ticket getById( Long ticketId)
  {
    return webClient.get()
                    .uri( "http://ticketservice/")
                    .retrieve()
                    .bodyToMono( Ticket.class).block();
  }

  ++++++++++++++++++++++++++++++++++++++++++++++++
}
