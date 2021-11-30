package casa.lead.domain.service.read.impl;

import casa.lead.domain.entity.Employee;
import casa.lead.domain.repository.EmployeeRepository;
import casa.lead.domain.service.read.IEmployeeQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeQueryServiceImpl implements IEmployeeQueryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Long id) {
        Mono<Employee> employeeMono = employeeRepository.findById(id);
        try {
            return employeeMono.toFuture().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NullPointerException();
        }
    }

    @Override
    public List<Employee> findByName(String name) {
        Mono<List<Employee>> e = employeeRepository.findByName(name).collectList();
        try {
            return e.toFuture().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw  new NullPointerException();
        }
    }

    @Override
    public List<Employee> findAll() {
        Mono<List<Employee>> e = employeeRepository.findAll().collectList();
        try {
            return e.toFuture().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw  new NullPointerException();
        }
    }
}
