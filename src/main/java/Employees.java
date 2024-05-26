public class Employees {
    private long id;
    private  String name_employees;
    private long phone_employees;
    private  boolean status_employees;
    private String date_;

    public Employees() {

    }


    @Override
    public String toString() {
        String Employees = "employees{" +
                "id=" + id +
                ", name_employees='" + name_employees + '\'' +
                ", phone_employees='" + phone_employees + '\'' +
                ", status_employees='" + status_employees + '\'' +
                ", date_='" + date_ + '\'' +
                '}';
        return Employees;
    }

    public Employees(long id, String name_employees, long phone_employees, boolean status_employees, String date_) {
        this.id = id;
        this.name_employees = name_employees;
        this.phone_employees = phone_employees;
        this.status_employees = status_employees;
        this.date_ = date_;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_employees() {
        return name_employees;
    }

    public void setName_employees(String name_employees) {
        this.name_employees = name_employees;
    }

    public long getPhone_employees() {
        return phone_employees;
    }

    public void setPhone_employees(long phone_employees) {
        this.phone_employees = phone_employees;
    }

    public boolean isStatus_employees() {
        return status_employees;
    }

    public void setStatus_employees(boolean status_employees) {
        this.status_employees = status_employees;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }
}
