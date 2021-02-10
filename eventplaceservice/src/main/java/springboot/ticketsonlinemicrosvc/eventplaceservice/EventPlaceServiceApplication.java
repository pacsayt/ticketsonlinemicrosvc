package springboot.ticketsonlinemicrosvc.eventplaceservice;

import brave.sampler.Sampler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * http://localhost:8011/h2-console
 *
 *
 * https://howtodoinjava.com/spring-cloud/microservices-monitoring/
 *
 * pt++ : ++
 * https://www.baeldung.com/spring-cloud-netflix-eureka
 *
 */
@SpringBootApplication
@EnableEurekaClient // pt++ : @EnableEurekaServer - must be a separate app, as the server cannot offer services
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EventPlaceServiceApplication implements CommandLineRunner
{
	private static final Logger LOG = LoggerFactory.getLogger( EventPlaceServiceApplication.class);

	@Autowired
//	@Lazy
	private EurekaClient eurekaClient; // pt++ : https://www.baeldung.com/spring-cloud-netflix-eureka

	@Value( "${spring.application.name}")
	private String applicationName;

	public static void main(String[] args)
	{
		SpringApplication.run( EventPlaceServiceApplication.class, args);
	}

	public void run( String[] args)
	{
		LOG.info( "pt++ EventPlaceServiceApplication::run() applicationName=" + applicationName);
		LOG.info( "pt++ EventPlaceServiceApplication::run() eurekaClient=" + eurekaClient);

		Application application = eurekaClient.getApplication( applicationName);
		LOG.info( "pt++ EventPlaceServiceApplication::run() application=" + application);

//		InstanceInfo instanceInfo = application.getInstances().get(0);
//		String hostname = instanceInfo.getHostName();
//		int port = instanceInfo.getPort();

		LOG.info( "pt++ EventPlaceServiceApplication::run() eurekaClient.getApplication( applicationName)" + eurekaClient.getApplication( applicationName));
//		LOG.info( "pt++ EventPlaceServiceApplication::run() eurekaClient.getApplication( applicationName).getName()" + eurekaClient.getApplication( applicationName).getName()); pt++ : TODO NPE
	}

	@Bean
	public Sampler getSampler() { // pt++ : a loggolas kozponti helyen (Sleuth ? Zipkin) valo megjelenitesehez kell
		return Sampler.ALWAYS_SAMPLE; // pt++ : https://cloud.spring.io/spring-cloud-sleuth/2.0.x/multi/multi__sampling.html
	}
}