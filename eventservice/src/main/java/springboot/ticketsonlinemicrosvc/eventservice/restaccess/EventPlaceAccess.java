package springboot.ticketsonlinemicrosvc.eventservice.restaccess;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;

/**
 * Spring 5 WebClient
 * https://www.baeldung.com/spring-5-webclient
 * pt++ : complicated explanation used brom R Baxter's projcet
 *  Finally, the WebClient interface has a single implementation
 *  the DefaultWebClient class which we'll be working with.
 *
 *  The Guide to RestTemplate
 *  https://www.baeldung.com/rest-template
 *
 *  Spring Framework 5:                                                 !
 *  ... if we're developing new applications or migrating an old one,   !
 *  it's a good idea to use WebClient.                                  !
 *  Moving forward, RestTemplate will be deprecated in future versions. !
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ !
 */
public class EventPlaceAccess
{
  @Autowired
  private WebClient webClient;

  // https://howtodoinjava.com/spring-webflux/webclient-get-post-example/
  public Mono<EventPlace> getById(Long eventPlaceId)
  {
    // pt++ : If we are only interested in response body entity the using methods retrieve() and then bodyToFlux() and bodyToMono() will serve the purpose.
    //        Will throw WebClientException If response status code is 4xx (client error) or 5xx (Server error) i.e. there is no response body
    // pt++ : Else, use method exchange() which will return the ClientResponse which has all the response elements such as status, headers and response body as well.
    return webClient.get().uri("http://eventplaceservice/" + eventPlaceId).retrieve().bodyToMono( EventPlace.class);
  }

  /**
  Mono vs Flux
  https://dimitr.im/difference-between-mono-and-flux

   abstract class Mono<T> implements CorePublisher<T> can either return zero or one result before completing,
   abstract class Flux<T> implements CorePublisher<T> can return zero to many, possibly infinite, results before completing.

   */
  public Flux<EventPlace> getAll()
  {
    // pt++ : If we are only interested in response body entity the using methods retrieve() and then bodyToFlux() and bodyToMono() will serve the purpose.
    //        Will throw WebClientException If response status code is 4xx (client error) or 5xx (Server error) i.e. there is no response body
    // pt++ : Else, use method exchange() which will return the ClientResponse which has all the response elements such as status, headers and response body as well.
    return webClient.get().uri("http://eventplaceservice/").retrieve().bodyToFlux( EventPlace.class);
  }

}
