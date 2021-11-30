package casa.lead.domain.repository;

import casa.lead.domain.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveSortingRepository<Employee, Long> {
    Flux<Employee> findByName(String name);
}
