package restaurant.ProjectApahida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.ProjectApahida.model.Dish;

import java.util.Optional;
@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {
    void deleteDishById(Long id);
    Optional<Dish> findDishById(Long id);

}
