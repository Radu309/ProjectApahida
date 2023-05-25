package restaurant.ProjectApahida.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.ProjectApahida.model.Employee;
import restaurant.ProjectApahida.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee){
        Employee employee = employeeService.addEmployee(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee newEmployee){
        Employee employee = employeeService.updateEmployee(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateEmployee(@RequestBody Employee newEmployee){
        Boolean employee = employeeService.authenticateEmployee(newEmployee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
