package restaurant.ProjectApahida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.ProjectApahida.model.Employee;

import java.util.Optional;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findEmployeeByUsername(String username);

}
