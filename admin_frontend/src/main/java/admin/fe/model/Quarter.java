package admin.fe.model;

import java.util.List;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.SimpleCategoryModel;

public class Quarter {
	
	private List<Month> months;
    private int quarter;
     
    public Quarter(int quarter, List<Month> months) {
        this.months = months;
        this.quarter = quarter;
    }
 
    public ListModel<Month> getMonths() {
        return new ListModelList<Month>(this.months);
    }
     
    public int getQuarter() {
        return quarter;
    }
 
    public double getBurhanHigh() {
        double total = 0;
 
        for (Month month : months) {
            total += month.getHigh();
        }
 
        return total / months.size();
    }
 
    public double getBurhanLow() {
        double total = 0;
 
        for (Month month : months) {
            total += month.getLow();
        }
 
        return total / months.size();
    }
 
    public double getAverageVolume() {
        double total = 0;
 
        for (Month month : months) {
            total += month.getVolume();
        }
 
        return total / months.size();
    }
 
    public CategoryModel getChartModel() {
         
        CategoryModel categoryModel = new SimpleCategoryModel();
         
        for(Month month : months) {
            categoryModel.setValue("Performance", month.getMonth(), month.getVolume());
        }
         
        return categoryModel;
         
    }

}
