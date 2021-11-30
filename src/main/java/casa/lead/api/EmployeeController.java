package casa.lead.api;

import casa.lead.api.response.BaseResponse;
import casa.lead.domain.entity.Employee;
import casa.lead.domain.service.read.IEmployeeQueryService;
import casa.lead.domain.service.write.IEmployeeCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeCommandService employeeCommandService;
    @Autowired
    private IEmployeeQueryService employeeQueryService;

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeCommandService.create(e);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<?>> findById(@PathVariable("id") Long id) {
        Employee e = employeeQueryService.findById(id);
        return new ResponseEntity<>(BaseResponse.success(e), HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<List<Employee>>> findByName(@PathVariable("name") String name) {
        List<Employee> result = employeeQueryService.findByName(name);
        return new ResponseEntity<>(BaseResponse.success(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<List<Employee>>> findAll() {
        List<Employee> result = employeeQueryService.findAll();
        return new ResponseEntity<>(BaseResponse.success(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<Employee>> update(@RequestBody Employee e) {
        Employee result = employeeCommandService.update(e);
        return ResponseEntity.ok(BaseResponse.success(result));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable("id") Long id) {
        employeeCommandService.delete(id);
        return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
    }
}
