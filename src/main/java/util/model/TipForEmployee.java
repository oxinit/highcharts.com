package util.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TipForEmployee {
  //  @CsvBindByName(column = "Highsoft employees (x)")
  @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date date;
 //   @CsvBindByName(column = "DateTime")
    @CsvBindByPosition(position = 0)
   private String nameAndStatus;
 //   @CsvBindByName(column = "Highsoft employees (y)")
    @CsvBindByPosition(position = 2)
   private String quantity;



    public TipForEmployee(Date date, String nameAndStatus, String quantity) {
        this.date = date;
        this.nameAndStatus = nameAndStatus;
        this.quantity = quantity;
    }

    public TipForEmployee() {

    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY");
        return   dateFormat.format(date);}
    public String getEmployeeNameAndStatus() {
        return nameAndStatus;
    }
    public String getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "TipForEmployee{" +
                "date='" + getDate() + '\'' +
                ", employeeNameAndStatus='" + nameAndStatus + '\'' +
                ", totalEmployees='" + quantity + '\'' +
                '}';
    }
}
