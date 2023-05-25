package restaurant.ProjectApahida.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.ProjectApahida.exception.UserNotFoundException;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.repository.DishRepo;

import java.util.List;

@Service
public class DishService {
    private final DishRepo dishRepo;

    @Autowired
    public DishService(DishRepo dishRepo) {
        this.dishRepo = dishRepo;
    }

    public Dish addDish(Dish dish){
        return dishRepo.save(dish);
    }
    public List<Dish> findAllDishes(){
        return dishRepo.findAll();
    }
    public Dish updateDish(Dish dish){
        return dishRepo.save(dish);
    }
    public Dish findDishById(Long id){
        return dishRepo.findDishById(id).orElseThrow(() ->
                new UserNotFoundException("Dish by id " + id + " was not found"));
    }
    @Transactional
    public void deleteDish(Long id){
        dishRepo.deleteDishById(id);
    }
}
