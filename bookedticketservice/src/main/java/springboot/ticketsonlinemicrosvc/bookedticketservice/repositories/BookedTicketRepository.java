package springboot.ticketsonlinemicrosvc.bookedticketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;

public interface BookedTicketRepository extends JpaRepository<BookedTicketEntity, Long>
{
//  List<BookedTicketEntity> findByBookedTicketEvent(EventEntity event); pt++ : not here because m.s. db has only one DB
}