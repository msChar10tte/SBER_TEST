import java.util.List;

public class App {
    public static  void main(String[] args){
        List<Employees> employeesS = CRUDUtils.getEmployeesDate("SELECT * FROM employees;");
        System.out.println(employeesS);


        Employees employees = new Employees();
        employees.setName_employees("qweqwe");
        employees.setPhone_employees(1234567890);
        employees.setStatus_employees(true);
        employees.setDate_("2024-05-25");

        System.out.println(CRUDUtils.saveEmployees(employees));

        System.out.println(CRUDUtils.updateEmployees(4, true));

        System.out.println(CRUDUtils.deleteEmployees(1));

    }
}
