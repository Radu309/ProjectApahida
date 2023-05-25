package restaurant.ProjectApahida.service.sub.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import restaurant.ProjectApahida.interfaces.GenerateReports;
import restaurant.ProjectApahida.model.Dish;
import restaurant.ProjectApahida.model.Orders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GenerateExcel implements GenerateReports {
    @Override
    public void generate(List<Dish> dishList, List<Orders> ordersList){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Total Price");
        headerRow.createCell(2).setCellValue("Date");
        headerRow.createCell(3).setCellValue("Time");
        headerRow.createCell(4).setCellValue("Status");
        int rowNum = 1;
        for (Orders order : ordersList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getTotalPrice());
            row.createCell(2).setCellValue(order.getLocalDate().toString());
            row.createCell(3).setCellValue(order.getLocalTime().toString());
            row.createCell(4).setCellValue(order.getStatus());
        }
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("orders.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /// Dishes
        Workbook workbook1 = new XSSFWorkbook();

        Sheet sheet1 = workbook1.createSheet("Dishes");

        Row headerRow1 = sheet.createRow(0);
        headerRow1.createCell(0).setCellValue("ID");
        headerRow1.createCell(1).setCellValue("Name");
        headerRow1.createCell(2).setCellValue("Price");
        headerRow1.createCell(3).setCellValue("Stock");

        int rowNum1 = 1;
        for (Dish dish : dishList) {
            Row row = sheet1.createRow(rowNum++);
            row.createCell(0).setCellValue(dish.getId());
            row.createCell(1).setCellValue(dish.getName());
            row.createCell(2).setCellValue(dish.getPrice());
            row.createCell(3).setCellValue(dish.getStock());
        }

        for (int i = 0; i < headerRow1.getLastCellNum(); i++) {
            sheet1.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("dishes.xlsx")) {
            workbook1.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
