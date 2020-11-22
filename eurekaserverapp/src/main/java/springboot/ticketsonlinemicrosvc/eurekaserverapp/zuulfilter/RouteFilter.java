package springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteFilter extends ZuulFilter
{
  private static Logger LOG = LoggerFactory.getLogger( RouteFilter.class);

  @Override
  public String filterType()
  {
    LOG.info( "RouteFilter::filterType() ++++++++++++++++++++++++++++++++++++");

    return FilterType.route.name();
  }

  @Override
  public int filterOrder()
  {
    LOG.info( "RouteFilter::filterOrder() ++++++++++++++++++++++++++++++++++++");

    return 1;
  }

  @Override
  public boolean shouldFilter()
  {
    LOG.info( "RouteFilter::shouldFilter() ++++++++++++++++++++++++++++++++++++");

    return true;
  }

  @Override
  public Object run()
  {
    LOG.info( "RouteFilter::run() ++++++++++++++++++++++++++++++++++++");

    return null;
  }
}