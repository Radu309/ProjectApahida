package restaurant.ProjectApahida.service.sub.services;

import com.opencsv.CSVWriter;
import restaurant.ProjectApahida.interfaces.GenerateReports;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.model.Orders;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCSV implements GenerateReports {
    @Override
    public void generate(List<Dish> dishList,List<Orders> ordersList){
        try (CSVWriter writer = new CSVWriter(new FileWriter("orders.csv"))) {
            // Create header row
            String[] header = {"ID", "Total Price", "Date", "Time", "Status"};
            writer.writeNext(header);

            // Write data rows
            for (Orders order : ordersList) {
                String[] data = {
                        String.valueOf(order.getId()),
                        String.valueOf(order.getTotalPrice()),
                        order.getLocalDate().toString(),
                        order.getLocalTime().toString(),
                        order.getStatus()
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter("dishes.csv"))) {
            // Create header row
            String[] header = {"ID", "Name", "Price", "Stock"};
            writer.writeNext(header);

            // Write data rows
            for (Dish dish : dishList) {
                String[] data = {
                        String.valueOf(dish.getId()),
                        dish.getName(),
                        String.valueOf(dish.getPrice()),
                        String.valueOf(dish.getStock())
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
