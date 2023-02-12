package util.model;

import com.opencsv.bean.CsvBindByName;

public class TipForEmployee {
    @CsvBindByName
   private String date;
    @CsvBindByName(column = "name and status")
   private String nameandstatus;
    @CsvBindByName
   private String quantity;



    public TipForEmployee(String date, String nameandstatus, String quantity) {
        this.date = date;
        this.nameandstatus = nameandstatus;
        this.quantity = quantity;
    }

    public TipForEmployee() {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getEmployeeNameAndStatus() {
        return nameandstatus;
    }
    public String getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "TipForEmployee{" +
                "date='" + date + '\'' +
                ", employeeNameAndStatus='" + nameandstatus + '\'' +
                ", totalEmployees='" + quantity + '\'' +
                '}';
    }
}
