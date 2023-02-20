package util.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TipForEmployee {
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date date;
    @CsvBindByPosition(position = 0)
    private String nameAndStatus;
    @CsvBindByPosition(position = 2)
    private String quantity;

    public TipForEmployee(Date date, String nameAndStatus, String quantity) {
        this.date = date;
        this.nameAndStatus = nameAndStatus;
        this.quantity = quantity;
    }

    public TipForEmployee() {

    }

    public String getDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY");
        return dateFormat.format(date);
    }
    public Date getDate() {
        return date;
    }
    public String getEmployeeNameAndStatus() {
        return nameAndStatus;
    }

    public String getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
        return "TipForEmployee{" +
                "date='" + getDateAsString() + '\'' +
                ", employeeNameAndStatus='" + getEmployeeNameAndStatus() + '\'' +
                ", totalEmployees='" + getQuantity() + '\'' +
                '}';
    }
}
