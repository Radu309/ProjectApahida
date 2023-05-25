package restaurant.ProjectApahida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.ProjectApahida.model.Orders;

import java.util.Optional;
@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
    void deleteOrderById(Long id);
    Optional<Orders> findOrderById(Long id);

}
