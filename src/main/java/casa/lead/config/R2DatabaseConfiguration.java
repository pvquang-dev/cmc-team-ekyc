package casa.lead.config;

import casa.lead.domain.entity.Employee;
import casa.lead.domain.repository.EmployeeRepository;
import casa.lead.domain.service.write.IEmployeeCommandService;
import casa.lead.domain.service.read.IEmployeeQueryService;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class R2DatabaseConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(R2DatabaseConfiguration.class);

    @Autowired
    private IEmployeeQueryService employeeQueryService;

    @Autowired
    private IEmployeeCommandService employeeCommandService;

    @Bean()
    ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        // This will create our database table and schema
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("dbsetup.sql")));
        // This will drop our table after we are done so we can have a fresh start next run
//        initializer.setDatabaseCleaner(new ResourceDatabasePopulator(new ClassPathResource("cleanup.sql")));
        return initializer;
    }

    @Bean
    public CommandLineRunner reactiveDatabaseExample(EmployeeRepository employeeRepository) {
        return args -> {
            List<Employee> employees = Arrays.asList(
                    new Employee(null, "Ford", "Mustang"),
                    new Employee(null, "Ford", "Bronco"),
                    new Employee(null, "Chevy", "Silverado"),
                    new Employee(null, "Chevy", "Tahoe"),
                    new Employee(null, "Toyota", "Supra")
            );

            employees.forEach(e -> employeeCommandService.create(e));

//            employeeRepository.saveAll(employees).blockLast(Duration.ofSeconds(5));

            // Find one or more employees from the repository using a query
            /*employeeQueryService.findByName("Chevy").doOnNext(vehicle -> {
                logger.info(vehicle.toString());
            }).delayElement(Duration.ofSeconds(5));

            // Find a single vehicle by an ID, returns a Mono
            employeeQueryService.findById(1L).doOnNext(vehicle -> {
                logger.info(vehicle.toString());
            }).block(Duration.ofSeconds(5));

            // Print all employees from the repository
            employeeQueryService.findAll().doOnNext(vehicle -> {
                logger.info(vehicle.toString());
            }).delayElement(Duration.ofSeconds(5));

            // Print all employees that match the provided make "Ford"
            // block the thread until the mono is completed or the request times out (5 seconds)
            Mono<List<Employee>> vehicleListMono = employeeQueryService.findByName("Ford");
            List<Employee> employeeList = vehicleListMono.block(Duration.ofSeconds(5));

            Mono<List<Employee>> serviceAll = employeeQueryService.findAll();
            List<Employee> result = serviceAll.block();*/

            for (Employee v : employees) {
                logger.info(v.toString());
            }
        };
    }
}
