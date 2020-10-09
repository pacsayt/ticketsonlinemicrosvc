package springboot.ticketsonlinemicrosvc.eventservice.ticketsonlinemicrosvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicketEntity, Long>
{
  List<BookedTicketEntity> findByBookedTicketEventId(Long eventId);
}