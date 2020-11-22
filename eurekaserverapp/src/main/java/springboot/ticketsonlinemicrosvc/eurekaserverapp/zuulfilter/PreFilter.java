package springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PreFilter extends ZuulFilter
{
  private static Logger LOG = LoggerFactory.getLogger( PreFilter.class);

  @Override
  public String filterType()
  {
    LOG.info( "PreFilter::filterType() ++++++++++++++++++++++++++++++++++++");

    return FilterType.pre.name();
  }

  @Override
  public int filterOrder()
  {
    LOG.info( "PreFilter::filterOrder() ++++++++++++++++++++++++++++++++++++");

    return 1;
  }

  @Override
  public boolean shouldFilter()
  {
    LOG.info( "PreFilter::shouldFilter() ++++++++++++++++++++++++++++++++++++");

    return true;
  }

  @Override
  public Object run()
  {
    LOG.info( "PreFilter::run() ++++++++++++++++++++++++++++++++++++");

    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();

    LOG.info( "PreFilter::run() : Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());

    return null;
  }
}