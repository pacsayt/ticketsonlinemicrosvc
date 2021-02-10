package springboot.ticketsonlinemicrosvc.eventservice.restaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlaces;

/**
 * Spring 5 WebClient
 * https://www.baeldung.com/spring-5-webclient
 * pt++ : complicated explanation used from R Baxter's project
 * Finally, the WebClient interface has a single implementation
 * the DefaultWebClient class which we'll be working with.
 * ^ pt++ : did NOT work with Eureka (s. below)
 *
 * Spring WebClient – GET, PUT, POST, DELETE examples
 * https://howtodoinjava.com/spring-webflux/webclient-get-post-example/
 *
 * The Guide to RestTemplate
 * https://www.baeldung.com/rest-template
 *
 *  Spring Framework 5:                                                 !
 *  ... if we're developing new applications or migrating an old one,   !
 *  it's a good idea to use WebClient.                                  !
 *  Moving forward, RestTemplate will be deprecated in future versions. !
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ !
 */
@RefreshScope
@RestController
// @Component
public class EventPlaceServiceAccess
{
  private static final Logger LOG = LoggerFactory.getLogger( EventPlaceServiceAccess.class);

  public static final String EVENTPLACESERVICE_URL = "http://eventplaceservice/eventplace/";

//  @Autowired
//  private WebClient webClient; // = WebClient.create( "http://eventplaceservice"); - pt++ : won't replace service name with active URL

  @Autowired
  private RestTemplate restTemplate;

//  @Autowired
//  private EventPlaceControllerInterface eventPlaceController; - pt++ : won't replace service name with active URL

  public EventPlace getById( Long eventPlaceId)
  {
    LOG.info( "EventPlaceServiceAccess::getById() using RestTemplate.getForObject( http://eventplaceservice/eventplace/" + eventPlaceId + " +++++++++++++++++++++++++++++");

    return restTemplate.getForObject( "http://eventplaceservice/eventplace/" + eventPlaceId, EventPlace.class);
  }

  public EventPlaces getAll()
  {
    EventPlaces eventPlaces = restTemplate.getForObject( EVENTPLACESERVICE_URL, EventPlaces.class);
    LOG.info( "EventPlaceServiceAccess::getAll() using RestTemplate.getForObject( " + EVENTPLACESERVICE_URL + " +++++++++++++++++++++++++++++");

    return eventPlaces;
  }

  public EventPlace post( EventPlace eventPlaceToCreate)
  {
    LOG.info( "EventPlaceServiceAccess::post() using RestTemplate.postForObject( " + EVENTPLACESERVICE_URL + " +++++++++++++++++++++++++++++");

    return restTemplate.postForObject( EVENTPLACESERVICE_URL, eventPlaceToCreate, EventPlace.class);
  }

  public void put( EventPlace eventPlaceToModify)
  {
    restTemplate.put( EVENTPLACESERVICE_URL, eventPlaceToModify);
  }

  public void delete( EventPlace eventPlaceToDelete)
  {
    restTemplate.delete( EVENTPLACESERVICE_URL, eventPlaceToDelete);
  }


  // https://howtodoinjava.com/spring-webflux/webclient-get-post-example/

  /**
   * pt++ : If we are only interested in response body entity the using methods retrieve() and then bodyToFlux() or bodyToMono() will serve the purpose.
   * Will throw WebClientException If response status code is 4xx (client error) or 5xx (Server error) i.e. there is no response body
   * Else, use method exchange() which will return the ClientResponse which has all the response elements such as status, headers and response body as well.
   */
//  public Mono<EventPlace> getById( Long eventPlaceId)
//  {
//    return webClient.get()
//                    .uri( "http://eventplaceservice/eventplace/" + eventPlaceId)
//                    .retrieve()
//                    // .accept(MediaType.TEXT_PLAIN) pt+ : just as an example what is possible
//                    .bodyToMono( EventPlace.class);
//  }


//  public EventPlace getById( Long eventPlaceId)
//  {
//    LOG.info( "EventPlaceServiceAccess::getById() : eventPlaceId=" + eventPlaceId + " +++++++++++++++++++++++++++++");
//    return eventPlaceController.getById( eventPlaceId);
//  }

  /**
   Mono vs Flux
   https://dimitr.im/difference-between-mono-and-flux

   abstract class Mono<T> implements CorePublisher<T> can either return zero or one result before completing,
   abstract class Flux<T> implements CorePublisher<T> can return zero to many, possibly infinite, results before completing.

   */
//  public Mono<EventPlaces> getAll()
//  {
//    // pt++ : If we are only interested in response body entity the using methods retrieve() and then bodyToFlux() and bodyToMono() will serve the purpose.
//    //        Will throw WebClientException If response status code is 4xx (client error) or 5xx (Server error) i.e. there is no response body
//    // pt++ : Else, use method exchange() which will return the ClientResponse which has all the response elements such as status, headers and response body as well.
//    return webClient.get()
//            .uri("http://eventplaceservice/")
//            .retrieve()
//            .bodyToMono( EventPlaces.class);
//  }
//
//  public Mono<EventPlace> post( EventPlace eventPlaceToSave )
//  {
//    return webClient.post()  // pt++ : post() ???
//                    .uri( "http://eventplaceservice/")
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .body( Mono.just( eventPlaceToSave), EventPlace.class)
//                    .retrieve()
//                    .bodyToMono( EventPlace.class);
//  }
//
//  public Mono<EventPlace> put( EventPlace eventPlaceToSave )
//  {
//    return webClient.put()  // pt++ : post() ???
//                    .uri( "http://eventplaceservice/")
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .body( Mono.just( eventPlaceToSave), EventPlace.class)
//                    .retrieve()
//                    .bodyToMono( EventPlace.class);
//  }
}
