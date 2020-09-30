package springboot.ticketsonlinemicrosvc.eventservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * pt++ : example for an in interview asked question.
 *
 * Introduction to Spring MVC HandlerInterceptor
 * https://www.baeldung.com/spring-mvc-handlerinterceptor
 */
@Component
public class MessageHandlerInterceptor extends HandlerInterceptorAdapter // pt++ : implements AsyncHandlerInterceptor extends HandlerInterceptor
{
  private static Logger LOG = LoggerFactory.getLogger(MessageHandlerInterceptor.class);

  /**
   * This method is called before handling a request; it returns true, to allow the framework to send the request further
   * to the handler method (or to the next interceptor). If the method returns false, Spring assumes that request has been
   * handled and no further processing is needed.
   */
  @Override
  public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    LOG.info( "MessageHandlerInterceptor::preHandle() : [preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));

    return true; // pt++ : will forward to real handlers
  }

  /**
   * This hook runs when the HandlerAdapter is invoked the handler but DispatcherServlet is yet to render the view.
   */
  @Override
  public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception
  {
    LOG.info( "MessageHandlerInterceptor::postHandle() : [postHandle][" + request + "]");
  }

  /**
   * When a request is finished and the view is rendered, we may obtain request and response data, as well as information about exceptions, if any occurred:
   */
  @Override
  public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception
  {
    if ( ex != null )
    {
      ex.printStackTrace();
    }

    LOG.info( "MessageHandlerInterceptor::afterCompletion() : [afterCompletion][" + request + "][exception: " + ex + "]");
  }

  @Override
  public void afterConcurrentHandlingStarted( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    LOG.info( "MessageHandlerInterceptor::afterConcurrentHandlingStarted()");
  }

  private String getParameters( HttpServletRequest request)
  {
    StringBuffer posted = new StringBuffer();
    Enumeration<?> e = request.getParameterNames();

    if ( e != null )
    {
      posted.append("?");
    }

    while ( e.hasMoreElements() )
    {
      if ( posted.length() > 1 )
      {
        posted.append("&");
      }
      String curr = (String) e.nextElement();
      posted.append(curr + "=");
      if ( curr.contains("password")
              || curr.contains("pass")
              || curr.contains("pwd") )
      {
        posted.append("*****");
      }
      else
      {
        posted.append(request.getParameter(curr));
      }
    }

    String ip = request.getHeader("X-FORWARDED-FOR");
    String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;

    if ( ipAddr != null && !ipAddr.equals("") )
    {
      posted.append("&_psip=" + ipAddr);
    }

    return posted.toString();
  }

  private String getRemoteAddr( HttpServletRequest request)
  {
    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");

    if ( ipFromHeader != null && ipFromHeader.length() > 0 )
    {
      LOG.debug("MessageHandlerInterceptor::getRemoteAddr() : ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
      return ipFromHeader;
    }

    return request.getRemoteAddr();
  }
}
