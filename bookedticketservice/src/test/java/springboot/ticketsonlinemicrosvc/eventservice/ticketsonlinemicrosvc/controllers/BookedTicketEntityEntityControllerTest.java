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
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTickets;
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
    TicketEntity ticketEntity = new TicketEntity( 22L, 1, null, 234);
    BookedTicketEntity bookedTicketEntity = new BookedTicketEntity( 22L, 22L);

    when( mockBookedTicketService.findById( 22L)).thenReturn( Optional.of(bookedTicketEntity));

    mockMvc.perform(  get( "/bookedticket/{id}", 22L).
                      contentType( MediaType.APPLICATION_JSON))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(bookedTicketEntity, BookedTicketEntity.class));

    verify(mockBookedTicketService, times( 1)).findById( 22L);
  }

  @Test
  public void testFindAll() throws Exception
  {
    TicketEntity ticketEntity22 = new TicketEntity( 22L, 2, null, 222);
    TicketEntity ticketEntity33 = new TicketEntity( 33L, 3, null, 333);

    BookedTicketEntity bookedTicketEntity22 = new BookedTicketEntity( 22L, 22L);
    BookedTicketEntity bookedTicketEntity33 = new BookedTicketEntity( 33L, 33L);

    List<BookedTicketEntity> allBookedTicketEntities = List.of(bookedTicketEntity22, bookedTicketEntity33);

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
    TicketEntity ticketEntity22 = new TicketEntity( 22L, 2, null, 222);

    BookedTicketEntity bookedTicketEntityEmptyId = new BookedTicketEntity( 0L, 22L);
    BookedTicketEntity bookedTicketEntity1 = new BookedTicketEntity( 1L, 22L);

    when( mockBookedTicketService.save(bookedTicketEntityEmptyId)).thenReturn(bookedTicketEntity1);

    mockMvc.perform( post( "/bookedticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString(bookedTicketEntityEmptyId)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(bookedTicketEntity1, BookedTicketEntity.class));
  }

  @Test
  public void testPut() throws Exception
  {
    TicketEntity ticketEntity22 = new TicketEntity( 22L, 2, null, 222);

    BookedTicketEntity bookedTicketEntityEmptyId = new BookedTicketEntity( 0L, 22L);
    BookedTicketEntity bookedTicketEntity1 = new BookedTicketEntity( 1L, 22L);

    when( mockBookedTicketService.save(bookedTicketEntityEmptyId)).thenReturn(bookedTicketEntity1);

    mockMvc.perform( put( "/bookedticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString(bookedTicketEntityEmptyId)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(bookedTicketEntity1, BookedTicketEntity.class));
  }
}