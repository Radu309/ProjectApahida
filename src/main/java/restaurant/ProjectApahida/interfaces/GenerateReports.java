package restaurant.ProjectApahida.interfaces;

import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.model.Orders;

import java.util.List;

public interface GenerateReports {
    void generate(List<Dish> dishList, List<Orders> ordersList);
}
