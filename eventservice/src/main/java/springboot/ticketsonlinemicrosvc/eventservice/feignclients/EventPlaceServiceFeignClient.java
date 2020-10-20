package springboot.ticketsonlinemicrosvc.eventservice.feignclients;

import org.springframework.http.ResponseEntity;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlaces;

import java.util.Optional;

public class EventPlaceServiceFeignClient implements EventPlaceControllerInterface
{
  @Override
  public EventPlaces getAll()
  {
    return null;
  }

  @Override
  public EventPlace getById(Long eventPlaceId)
  {
    return null;
  }

  @Override
  public ResponseEntity<Object> post(EventPlace eventPlace)
  {
    return null;
  }

  @Override
  public ResponseEntity<Optional<EventPlace>> put(EventPlace eventPlace)
  {
    return null;
  }
}
