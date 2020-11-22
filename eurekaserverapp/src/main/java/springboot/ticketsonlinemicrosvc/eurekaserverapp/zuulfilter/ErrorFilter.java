package springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.ticketsonlinemicrosvc.eurekaserverapp.EurekaserverappApplication;

public class ErrorFilter extends ZuulFilter
{
  private static Logger LOG = LoggerFactory.getLogger( ErrorFilter.class);

  @Override
  public String filterType()
  {
    LOG.info( "ErrorFilter::filterType() ++++++++++++++++++++++++++++++++++++");

    return FilterType.error.name();
  }

  @Override
  public int filterOrder()
  {
    LOG.info( "ErrorFilter::filterOrder() ++++++++++++++++++++++++++++++++++++");

    return 1;
  }

  @Override
  public boolean shouldFilter()
  {
    LOG.info( "ErrorFilter::shouldFilter() ++++++++++++++++++++++++++++++++++++");

    return true;
  }

  @Override
  public Object run()
  {
    LOG.info( "ErrorFilter::run() ++++++++++++++++++++++++++++++++++++");

    return null;
  }
}