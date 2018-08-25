package admin.fe.model;

import java.util.List;

import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

public class Stock {

	private String name;
    private List<Quarter> quarters;
 
    public Stock(String name, double high, double low,
            double volume, List<Quarter> quarters) {
        this.name = name;
        this.quarters = quarters;
    }
 
    public String getName() {
        return name;
    }
 
    public double getAverageHigh() {
        double total = 0;
 
        for (Quarter quarter : quarters) {
            total += quarter.getBurhanHigh();
        }
 
        return total / quarters.size();
    }
 
    public double getAverageLow() {
        double total = 0;
 
        for (Quarter quarter : quarters) {
            total += quarter.getBurhanLow();
        }
 
        return total / quarters.size();
    }
 
    public double getAverageVolume() {
        double total = 0;
 
        for (Quarter quarter : quarters) {
            total += quarter.getAverageVolume();
        }
 
        return total / quarters.size();
    }
 
    public ListModel<Quarter> getQuarters() {
        return new ListModelList<Quarter>(quarters);
    }
	
}
