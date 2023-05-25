package restaurant.ProjectApahida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.ProjectApahida.model.Admin;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    void deleteAdminById(Long id);

    Optional<Admin> findAdminById(Long id);

    Optional<Admin> findAdminByUsername(String username);
}
