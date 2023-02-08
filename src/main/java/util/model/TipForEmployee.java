package util.model;

public class TipForEmployee {
    String date;
    String employeeNameAndStatus;
    String totalEmployees;
    public TipForEmployee(String date, String employeeNameAndStatus, String totalEmployees) {
        this.date = date;
        this.employeeNameAndStatus = employeeNameAndStatus;
        this.totalEmployees = totalEmployees;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setEmployeeNameAndStatus(String employeeNameAndStatus) {
        this.employeeNameAndStatus = employeeNameAndStatus;
    }

    public void setTotalEmployees(String totalEmployees) {
        this.totalEmployees = totalEmployees;
    }



    public String getDate() {
        return date;
    }

    public String getEmployeeNameAndStatus() {
        return employeeNameAndStatus;
    }

    public String getTotalEmployees() {
        return totalEmployees;
    }

    @Override
    public String toString() {
        return "TipForEmployee{" +
                "date='" + date + '\'' +
                ", employeeNameAndStatus='" + employeeNameAndStatus + '\'' +
                ", totalEmployees='" + totalEmployees + '\'' +
                '}';
    }
}
