package springboot.ticketsonlinemicrosvc.eventservice.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;

import java.sql.Timestamp;
// import java.util.Date; pt++ : the database creates Timestamp (that inherits from Date)
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>
{
  List<EventEntity> findByName(String name);

  List<EventEntity> findByDate(Timestamp date);

  Optional<EventEntity> findByNameAndDate(String name, Timestamp date);
}
