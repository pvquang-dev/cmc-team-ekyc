package casa.lead.domain.service.write;

import casa.lead.domain.entity.Employee;

public interface IEmployeeCommandService {
    void create(Employee e);

    Employee update(Employee e);

    void delete(Long id);
}
