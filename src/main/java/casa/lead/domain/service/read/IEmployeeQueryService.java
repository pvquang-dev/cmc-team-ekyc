package casa.lead.domain.service.read;

import casa.lead.domain.entity.Employee;

import java.util.List;

public interface IEmployeeQueryService {
    Employee findById(Long id);

    List<Employee> findByName(String name);

    List<Employee> findAll();
}
