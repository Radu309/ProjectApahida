package restaurant.ProjectApahida.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.ProjectApahida.exception.UserNotFoundException;
import restaurant.ProjectApahida.model.DataRequest;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.model.Orders;
import restaurant.ProjectApahida.repository.DishRepo;
import restaurant.ProjectApahida.repository.OrderRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final DishRepo dishRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, DishRepo dishRepo){
        this.orderRepo = orderRepo;
        this.dishRepo = dishRepo;
    }
    public Orders addOrder(Orders orders){
        return orderRepo.save(orders);
    }
    public List<Orders> findAllOrders(){
        return orderRepo.findAll();
    }
    public Orders updateOrder(Orders orders){
        Orders newOrder = findOrderById(orders.getId());
        newOrder.setStatus(orders.getStatus());
        return orderRepo.save(newOrder);
    }
    public Orders findOrderById(Long id){
        return orderRepo.findOrderById(id).orElseThrow(() ->
                new UserNotFoundException("Order by id " + id + " was not found"));
    }
    public void deleteOrder(Long id){
        orderRepo.deleteOrderById(id);
    }

    public Orders orderWithDishes(Long orderId, Long dishId){
        List<Dish> dishList = null;

        Orders orders = orderRepo.findById(orderId).get();
        Dish dish = dishRepo.findById(dishId).get();

        dishList = orders.getDishList();
        dishList.add(dish);
        orders.setDishList(dishList);
        float totalPrice = 0.0f;
        for(Dish dishAux: dishList)
            totalPrice += dishAux.getPrice();
        orders.setTotalPrice(totalPrice);
        return orderRepo.save(orders);
    }

    public List<Orders> selectOrdersBetweenTwoDates(DataRequest data){
        List<Orders> orderList = orderRepo.findAll();
        List<Orders> resultOrders = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(data.getStartDate());
        LocalDate endDate = LocalDate.parse(data.getEndDate());
        for(Orders order: orderList){
            if (order.getLocalDate().isEqual(startDate) || order.getLocalDate().isEqual(endDate) ||
                    (order.getLocalDate().isAfter(startDate) && order.getLocalDate().isBefore(endDate))){
                resultOrders.add(order);
            }
        }
        return resultOrders;
    }
    public List<Dish> selectTopTenDishes(DataRequest data) {
        LocalDate startDate = LocalDate.parse(data.getStartDate());
        LocalDate endDate = LocalDate.parse(data.getEndDate());

        List<Orders> orderList = orderRepo.findAll();
        List<Dish> dishList = dishRepo.findAll();

        Map<Dish, Integer> dishMap = new HashMap<>();
        for (Dish dish : dishList) {
            dishMap.put(dish, 0);
        }


        orderList.stream()
                .filter(order -> order.getLocalDate().isAfter(startDate))
                .filter(order -> order.getLocalDate().isBefore(endDate))
                .filter(order -> order.getLocalDate().isEqual(startDate))
                .filter(order -> order.getLocalDate().isEqual(endDate))
                .forEach(order -> {
                    order.getDishList().forEach(dish -> {
                        int count = dishMap.get(dish);
                        dishMap.put(dish, count + 1);
                    });
                });
        /*
        for(Orders order: orderList) {
            if(order.getLocalDate().isAfter(startDate) && order.getLocalDate().isBefore(endDate)) {
                for (Dish dish : order.getDishList()) {
                    for(var a: dishMap.entrySet()){
                        if(a.getKey().equals(dish.getName()))
                            a.setValue(a.getValue()+1);
                    }
                }
            }
        }
         */

        return dishMap.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
