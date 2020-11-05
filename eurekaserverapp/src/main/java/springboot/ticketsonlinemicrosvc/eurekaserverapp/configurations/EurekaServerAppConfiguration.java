package springboot.ticketsonlinemicrosvc.eurekaserverapp.configurations;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

/**
 *                                http://localhost:8761/
 */
@Configuration
@EnableEurekaServer
@EnableAdminServer  // pt++ : This annotation provides Spring Boot Admin configuration. http://localhost:8761/admin
@EnableConfigServer // pt++ : -> pacsayt/ticketsonlinecfg produces an exception at startup :
// java.lang.IllegalStateException: Error processing condition on org.springframework.cloud.config.server.config.CompositeRepositoryConfiguration.searchPathCompositeEnvironmentRepository
// https://github.com/spring-cloud/spring-cloud-config/issues/942
public class EurekaServerAppConfiguration
{
}