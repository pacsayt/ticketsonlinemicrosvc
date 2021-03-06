package springboot.ticketsonlinemicrosvc.eventservice.ticketsonlinemicrosvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import springboot.ticketsonlinemicrosvc.bookedticketservice.controllers.BookedTicketController;
import springboot.ticketsonlinemicrosvc.bookedticketservice.services.BookedTicketService;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTickets;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest( controllers = BookedTicketController.class)
public class BookedTicketEntityEntityControllerTest
{
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookedTicketService mockBookedTicketService;

  @Test
  public void testFindOneById() throws Exception
  {
    Ticket ticket = new Ticket( 22L, 1, null, 234);
    BookedTicket bookedTicket = new BookedTicket( 22L, ticket);

    when( mockBookedTicketService.findById( 22L)).thenReturn( Optional.of( bookedTicket));

    mockMvc.perform(  get( "/bookedticket/{id}", 22L).
                      contentType( MediaType.APPLICATION_JSON))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( bookedTicket, BookedTicket.class));

    verify(mockBookedTicketService, times( 1)).findById( 22L);
  }

  @Test
  public void testFindAll() throws Exception
  {
    Ticket ticket22 = new Ticket( 22L, 2, null, 222);
    Ticket ticket33 = new Ticket( 33L, 3, null, 333);

    BookedTicket bookedTicket22 = new BookedTicket( 22L, ticket22);
    BookedTicket bookedTicket33 = new BookedTicket( 33L, ticket33);

    List<BookedTicket> allBookedTicketEntities = List.of( bookedTicket22, bookedTicket33);

    BookedTickets bookedTickets = new BookedTickets(allBookedTicketEntities);

    when( mockBookedTicketService.findAll()).thenReturn(allBookedTicketEntities);

    mockMvc.perform(  get( "/bookedticket").
                      contentType( MediaType.APPLICATION_JSON))
            .andExpect( status().isOk())
            .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( bookedTickets, BookedTickets.class));

    verify(mockBookedTicketService, times( 1)).findAll();
  }

  @Test
  public void testPost() throws Exception
  {
    Ticket ticket22 = new Ticket( 22L, 2, null, 222);

    BookedTicket bookedTicketEmptyId = new BookedTicket( 0L, ticket22);
    BookedTicket bookedTicket1 = new BookedTicket( 1L, ticket22); // pt++ : ticket22 ???

    when( mockBookedTicketService.save( bookedTicketEmptyId)).thenReturn( bookedTicket1);

    mockMvc.perform( post( "/bookedticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString( bookedTicketEmptyId)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( bookedTicket1, BookedTicket.class));
  }

  @Test
  public void testPut() throws Exception
  {
    Ticket ticket22 = new Ticket( 22L, 2, null, 222);

    BookedTicket bookedTicketEmptyId = new BookedTicket( 0L, ticket22);
    BookedTicket bookedTicket1 = new BookedTicket( 1L, ticket22);

    when( mockBookedTicketService.save( bookedTicketEmptyId)).thenReturn( bookedTicket1);

    mockMvc.perform( put( "/bookedticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString( bookedTicketEmptyId)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( bookedTicket1, BookedTicketEntity.class));
  }
}