package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.Tickets;
import springboot.ticketsonline.services.TestBase;
import springboot.ticketsonlinemicrosvc.ticketservice.services.TicketService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest( controllers = TicketController.class)
public class TicketEntityControllerTest extends TestBase
{
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TicketService mockTicketService;


  @Test
  public void testFindOneById() throws Exception
  {
//    EventPlace eventPlaceReturnedByService =  new EventPlace( 111L, "Name_1", 10);
//    EventEntity eventReturnedByService = new EventEntity( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceReturnedByService);
    TicketEntity ticketEntityReturnedByService = new TicketEntity( 11L, 1, 11L, 111);

    when( mockTicketService.findById( 11L)).thenReturn( Optional.of(ticketEntityReturnedByService));

    mockMvc.perform( get( "/ticket/{id}", 11L).contentType( "application/json"))

           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(ticketEntityReturnedByService, TicketEntity.class));

    verify( mockTicketService, times( 1)).findById( 11L);
  }

  @Test
  public void testFindAll() throws Exception
  {
    List<TicketEntity> ticketEntityList = List.of( new TicketEntity( 10L, 2, null, 100),
            new TicketEntity( 11L, 1, null, 111),
            new TicketEntity( 12L, 3, null, 112),
            new TicketEntity( 13L, 4, null, 113));

    when( mockTicketService.findAll()).thenReturn(ticketEntityList);

    Tickets tickets = new Tickets(ticketEntityList);

    mockMvc.perform( get( "/ticket").contentType( MediaType.APPLICATION_JSON))
            .andExpect( status().isOk())
            .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( tickets, Tickets.class));
  }

  @Test
  public void testPost() throws Exception
  {
    TicketEntity ticketEntityToSave = new TicketEntity( 11L, 1, null, 111);
    TicketEntity ticketEntityAfterSave = new TicketEntity( 1L, 1, null, 111);

    when( mockTicketService.save(ticketEntityToSave)).thenReturn(ticketEntityAfterSave);

    mockMvc.perform( post( "/ticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString(ticketEntityToSave)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(ticketEntityAfterSave, TicketEntity.class));

    verify( mockTicketService, times( 1)).save(ticketEntityToSave);
  }

  @Test
  public void testPut() throws Exception
  {
    TicketEntity ticketEntityToSave = new TicketEntity( 11L, 1, null, 111);
    TicketEntity ticketEntityAfterSave = new TicketEntity( 11L, 1, null, 111);

    when( mockTicketService.save( any())).thenReturn(ticketEntityAfterSave);

    mockMvc.perform( put( "/ticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString(ticketEntityToSave)))
            .andExpect( status().isOk())
//            .andExpect( jsonPath("id", is( 11L)))
            .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(ticketEntityAfterSave, TicketEntity.class));

//    ArgumentCaptor<TicketEntity> ticketArgCapt = ArgumentCaptor.forClass( TicketEntity.class);

//TicketEntity t =    verify( mockTicketService, times( 1)).save( ticketEntityToSave); <- null
//Long id = t.getiD(); //.compareTo( 1L);

  }
}
