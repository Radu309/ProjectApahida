package restaurant.ProjectApahida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.ProjectApahida.interfaces.GenerateReports;
import restaurant.ProjectApahida.model.DataRequest;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.model.Orders;
import restaurant.ProjectApahida.service.OrderService;
import restaurant.ProjectApahida.service.sub.services.ReportsFactory;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ReportsFactory reportsFactory;

    public OrderController(OrderService orderService,ReportsFactory reportsFactory) {
        this.orderService = orderService;
        this.reportsFactory = reportsFactory;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> ordersList = orderService.findAllOrders();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") Long id){
        Orders orders = orderService.findOrderById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Orders> addOrder(@RequestBody Orders orders){
        Orders newOrders = orderService.addOrder(orders);
        return new ResponseEntity<>(newOrders, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders){
        Orders newOrders = orderService.updateOrder(orders);
        return new ResponseEntity<>(newOrders, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{orderId}/dish/{dishId}")
    public Orders orderWithDishes(
            @PathVariable("orderId") Long orderId,
            @PathVariable("dishId") Long dishId){

       return orderService.orderWithDishes(orderId, dishId);
    }
    @PostMapping("dates/{id}")
    public void generateReports(
            @PathVariable("id") int reportType,
            @RequestBody DataRequest requestData){
        System.out.println("Here");
        List<Dish> dishList = orderService.selectTopTenDishes(requestData);
        List<Orders> ordersList = orderService.selectOrdersBetweenTwoDates(requestData);
        if(reportType == 0){
            System.out.println(requestData.getEndDate());
            //System.out.println(requestData.getStartDate());
            //Excel
            GenerateReports reports= reportsFactory.getReports("Excel");
            reports.generate(dishList,ordersList);
        }
        if(reportType == 1){
            System.out.println(requestData);
            //CSV
            GenerateReports reports= reportsFactory.getReports("CSV");
            reports.generate(dishList,ordersList);
        }
    }
}
