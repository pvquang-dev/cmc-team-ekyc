package casa.lead.domain.service.write.impl;

import casa.lead.domain.entity.Employee;
import casa.lead.domain.repository.EmployeeRepository;
import casa.lead.domain.service.write.IEmployeeCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCommandServiceImpl implements IEmployeeCommandService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Employee e) {
        employeeRepository.save(e).subscribe();
    }

    @Override
    public Employee update(Employee e) {
        try {
            return employeeRepository.save(e).toFuture().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NullPointerException();
        }
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id).subscribe();
    }
}
