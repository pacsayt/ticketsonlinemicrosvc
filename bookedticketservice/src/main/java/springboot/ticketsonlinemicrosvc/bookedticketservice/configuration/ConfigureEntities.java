package springboot.ticketsonlinemicrosvc.bookedticketservice.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * FactoryBean threw exception on object creation; ...
 * Not a managed type: class springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace
 * https://stackoverflow.com/questions/28664064/spring-boot-not-a-managed-type/34884871#34884871
 *
 * pt++ : but will OMIT schema.sql !
 */
@Configuration
@EntityScan("springboot.ticketsonlinemicrosvc.common.entities.bookedticket")
public class ConfigureEntities
{
}
