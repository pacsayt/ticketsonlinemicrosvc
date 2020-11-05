package springboot.ticketsonlinemicrosvc.eventservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * *****************************************************************************************
 * http://localhost:8012/event
 * http://localhost:8012/event/11 12 -> null
 * *****************************************************************************************
 *
 * Spring Boot With H2 Database
 * https://www.baeldung.com/spring-boot-h2-database
 *
 *  pt++ : H2 console : http://localhost:8080/h2-console/ -> JDBC URL : =jdbc:h2:mem:test
 *
 * Session :
 *   Instances may exist in one of the following three states :
 *   transient − A new instance of a persistent class, which is not associated with a Session
 *               and has no representation in the database
 *   persistent − instance has a representation in the database, an identifier value and is
 *                associated with a Session.
 *   detached −  the Hibernate Session is closed
 *
 *   Lots of Session's methods is listed :
 *   https://www.tutorialspoint.com/hibernate/hibernate_sessions.htm
 *
 * Session session = factory.openSession();
 * Transaction tx = null;
 *
 * try {
 *    tx = session.beginTransaction();
 *    ...
 *    tx.commit();
 * } catch (Exception e) {
 *    if (tx!=null) tx.rollback();
 *    e.printStackTrace();
 * } finally {
 *    session.close();
 * }
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableFeignClients
public class EventServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(EventServiceApplication.class, args);
	}

	@Bean
	public Sampler getSampler()
	{
		// pt++ : for centralized logging (Sleuth ? Zipkin)
		return Sampler.ALWAYS_SAMPLE; // pt++ : https://cloud.spring.io/spring-cloud-sleuth/2.0.x/multi/multi__sampling.html
	}
}