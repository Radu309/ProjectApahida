package restaurant.ProjectApahida.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.ProjectApahida.exception.UserNotFoundException;
import restaurant.ProjectApahida.model.Employee;
import restaurant.ProjectApahida.repository.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean authenticateEmployee(Employee employee){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        Optional<Employee> optionalEmployee = employeeRepo.findEmployeeByUsername(employee.getUsername());
        if(optionalEmployee.isPresent()){
            Employee newEmployee = optionalEmployee.get();
            return bCrypt.matches(employee.getPassword(), newEmployee.getPassword());
        }
        return false;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(String.valueOf(UUID.randomUUID()));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        //System.out.println(employee.getPassword());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }
    public Employee updateEmployee(Employee employee){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepo.save(employee);
    }
    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(() ->
                new UserNotFoundException("Employee by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}
