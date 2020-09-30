package springboot.ticketsonlinemicrosvc.eventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
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
public class TicketsOnlineApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TicketsOnlineApplication.class, args);

		while (true){}
	}
}
