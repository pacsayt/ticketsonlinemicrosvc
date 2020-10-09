package springboot.ticketsonlinemicrosvc.ticketservice.restaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.event.Event;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;

@Component
public class EventAccess
{
  @Autowired
  private WebClient webClient;

  public Mono<Event> getById( Long eventId)
  {
    return webClient.get()
                    .uri( "http://eventservice/" + eventId)
                    .retrieve()
                    .bodyToMono( Event.class);
  }

  public Mono<Event> post( Event eventToSave)
  {
    return webClient.post()
                    .uri( "http://eventservice/")
                    .body( Mono.just( eventToSave), Event.class)
                    .retrieve()
                    .bodyToMono( Event.class);
  }

  public Mono<Event> put( Event eventToSave)
  {
    return webClient.put()
                    .uri( "http://eventservice/")
                    .body( Mono.just( eventToSave), Event.class)
                    .retrieve()
                    .bodyToMono( Event.class);
  }

  public Flux<Event> getAll()
  {
    return webClient.get()
                    .uri( "http://eventservice/")
                    .retrieve()
                    .bodyToFlux( Event.class);
  }
}