package springboot.ticketsonlinemicrosvc.eventservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyMatchers
{
  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * @FunctionalInterface
   * public interface ResultMatcher {
   *   void match(MvcResult var1) throws Exception;
   *
   *   static ResultMatcher matchAll(ResultMatcher... matchers)
   *   ...
   * }
   */

  public <T> ResultMatcher containsObjectAsJson( Object expectedObject, Class<T> targetClass)
  {
    return mvcResult -> {
                          String responseContentAsJson = mvcResult.getResponse().getContentAsString();
                          T actualObject = objectMapper.readValue( responseContentAsJson, targetClass);
                          assertThat( actualObject).isEqualToComparingFieldByField( expectedObject);
                        };
  }

  public static ResponseBodyMatchers createResponseBodyMatcher()
  {
    return new ResponseBodyMatchers();
  }
}
