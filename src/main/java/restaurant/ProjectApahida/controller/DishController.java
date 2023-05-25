package restaurant.ProjectApahida.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dish>> getAllDishes(){
        List<Dish> dishList = dishService.findAllDishes();
        return new ResponseEntity<>(dishList, HttpStatus.OK);
    }
    @GetMapping("/find/{idDish}")
    public ResponseEntity<Dish> getDish(@PathVariable("idDish") Long id){
        Dish dish = dishService.findDishById(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish){
        Dish newDish = dishService.addDish(dish);
        return new ResponseEntity<>(newDish, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Dish> updateDish(@RequestBody Dish dish){
        Dish newDish = dishService.updateDish(dish);
        return new ResponseEntity<>(newDish, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id){
        dishService.deleteDish(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
