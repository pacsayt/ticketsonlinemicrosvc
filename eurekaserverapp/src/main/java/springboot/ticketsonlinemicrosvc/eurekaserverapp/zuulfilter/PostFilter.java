package springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostFilter extends ZuulFilter
{
  private static Logger LOG = LoggerFactory.getLogger( PostFilter.class);

  @Override
  public String filterType()
  {
    LOG.info( "PostFilter::filterType() ++++++++++++++++++++++++++++++++++++");

    return FilterType.post.name();
  }

  @Override
  public int filterOrder()
  {
    LOG.info( "PostFilter::filterOrder() ++++++++++++++++++++++++++++++++++++");

    return 1;
  }

  @Override
  public boolean shouldFilter()
  {
    LOG.info( "PostFilter::shouldFilter() ++++++++++++++++++++++++++++++++++++");

    return true;
  }

  @Override
  public Object run()
  {
    LOG.info( "PostFilter::run() ++++++++++++++++++++++++++++++++++++");

    return null;
  }
}
