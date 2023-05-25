package restaurant.ProjectApahida.service.sub.services;

import org.springframework.stereotype.Component;
import restaurant.ProjectApahida.interfaces.GenerateReports;
@Component
public class ReportsFactory {
    public GenerateReports getReports(String type){
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("Excel"))
            return new GenerateExcel();
        if(type.equalsIgnoreCase("CSV"))
            return new GenerateCSV();
        return null;
    }
}
