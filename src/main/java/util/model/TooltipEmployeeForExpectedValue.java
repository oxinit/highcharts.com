package util.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class TooltipEmployeeForExpectedValue {

    @CsvBindByName
    private String date;
    @CsvBindByName(column = "name and status")
    private String nameAndStatus;
    @CsvBindByName
    private String quantity;

    public TooltipEmployeeForExpectedValue() {
    }
    public TooltipEmployeeForExpectedValue(String date, String nameAndStatus, String quantity) {
        this.date = date;
        this.nameAndStatus = nameAndStatus;
        this.quantity = quantity;
    }


    public String getDate() {return date;}
    public String getEmployeeNameAndStatus() {
        return nameAndStatus;
    }
    public String getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        TipForEmployee that = (TipForEmployee) o;
        return
                Objects.equals(date, that.getDateAsString())&&
                Objects.equals(nameAndStatus, that.getEmployeeNameAndStatus())
               && quantity.contains(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, nameAndStatus, quantity);
    }

    @Override
    public String toString() {
        return "TooltipEmployeeForExpectedValue{" +
                "date='" + getDate() + '\'' +
                ", employeeNameAndStatus='" + getEmployeeNameAndStatus() + '\'' +
                ", totalEmployees='" + getQuantity() + '\'' +
                '}';
    }
}
