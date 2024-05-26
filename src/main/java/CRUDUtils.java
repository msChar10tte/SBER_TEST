
import java.awt.event.InputEvent;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

public class CRUDUtils {

    private static String INSERT_EMPLOYEES = "INSERT INTO employees(name_employees, phone_employees, status_employees, date_) VALUES (?, ?, ?, ?);";
    private static String UPDATE_EMPLOYEES = "UPDATE employees SET status_employees = ? WHERE id =?;";
    private static String DELETE_EMPLOYEES = "DELETE FROM employees WHERE id = ?;";

    static Logger logger = Logger.getLogger("MyLog");
    static String pattern = "yyyy-MM-dd";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    static String date;
    static FileHandler fh;

    static {
        date = simpleDateFormat.format(new Date());
        try {
            logger.setUseParentHandlers(false);
            fh = new FileHandler("MyLogFile_" + date + ".log", true);
            CRUDUtils.logger.addHandler(fh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Метод который позволяет вывести всю таблицу
     * @param quert сюда передаем запрос
     * @return Возращает всю таблицу
     */
    public  static List<Employees> getEmployeesDate(String quert){
        List<Employees> employees = new ArrayList<>();

        try(Connection connection =BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(quert)){

             ResultSet res = preparedStatement.executeQuery();

             while (res.next()){
                 long id = res.getLong("id");
                 String name_employees= res.getString("name_employees");
                 long phone_employees= res.getLong("phone_employees");
                 boolean status_employees= res.getBoolean("status_employees");
                 String date_ =  res.getString("date_");

                 employees.add(new Employees(id, name_employees ,phone_employees, status_employees, date_));
             }
            CRUDUtils.logger.log(Level.INFO, "Всё прошло успешно");
        }catch (SQLException e) {
            CRUDUtils.logger.log(Level.WARNING, "Произошла ошибка");
            throw new RuntimeException(e);
        }
        return  employees;
    }


    /**
     * Создает и записывает данные в бд
     *
     * @param employeesS принимает параметр для записи
     * @return возращет сохроненую запись
     */
    public  static List<Employees> saveEmployees(Employees employeesS){
        List<Employees> employees = new ArrayList<>();

        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES))
        {
            preparedStatement.setString(1,employeesS.getName_employees());
            preparedStatement.setLong(2,employeesS.getPhone_employees());
            preparedStatement.setBoolean(3,employeesS.isStatus_employees());
            preparedStatement.setString(4, employeesS.getDate_());

            preparedStatement.executeUpdate();

            PreparedStatement allEmployees = connection.prepareStatement("SELECT * FROM employees");
            ResultSet res = allEmployees.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name_employees= res.getString("name_employees");
                long phone_employees= res.getLong("phone_employees");
                boolean status_employees= res.getBoolean("status_employees");
                String date_ = String.valueOf(res.getDate("date_"));

                employees.add(new Employees(id, name_employees ,phone_employees, status_employees, date_));
            }
            CRUDUtils.logger.log(Level.INFO, "Всё прошло успешно");
        }catch (SQLException e) {
            CRUDUtils.logger.log(Level.WARNING, "Произошла ошибка");
            throw new RuntimeException(e);
        }
        return employees;
    }


    /**
     * Изменение данных в бд , В данном случии статус сотркдника.
     * @param employeesID Нужно UD сотрудника
     * @param status_employeesS статус сотрудника в boolean, true - сотрудник работает, false- сотрудни не работает
     * @return Возращает изменёный статус сотрудника
     */
    public static List<Employees> updateEmployees(long employeesID, boolean status_employeesS){
        List<Employees> updateEmployees = new ArrayList<>();

        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES))
        {

            preparedStatement.setBoolean(1,status_employeesS);
            preparedStatement.setLong(2,employeesID);
            preparedStatement.executeUpdate();


            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM employees");
            ResultSet res = allStudent.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name_employees= res.getString("name_employees");
                long phone_employees= res.getLong("phone_employees");
                boolean status_employees= res.getBoolean("status_employees");
                String date_ = res.getString("date_");

                updateEmployees.add(new Employees(id, name_employees ,phone_employees, status_employees, date_));
            }
            CRUDUtils.logger.log(Level.INFO, "Всё прошло успешно");
        }catch (SQLException e) {
            CRUDUtils.logger.log(Level.WARNING, "Произошла ошибка");
            throw new RuntimeException(e);
        }
        return updateEmployees;
    }


    /**
     * Удаление из базы данных, удаляем через поиск id
     * @param studentID Нужен ID для удаления записи
     * @return возращает результат одвлённой строки
     */
    public static  List<Employees> deleteEmployees(long studentID){
        List<Employees> deleteEmployees = new ArrayList<>();
        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEES))
        {

            preparedStatement.setLong(1,studentID);
            preparedStatement.executeUpdate();

            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM employees");
            ResultSet res = allStudent.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name_employees= res.getString("name_employees");
                long phone_employees= res.getLong("phone_employees");
                boolean status_employees= res.getBoolean("status_employees");
                String date_ = res.getString("date_");

                deleteEmployees.add(new Employees(id, name_employees ,phone_employees, status_employees, date_));
            }
            CRUDUtils.logger.log(Level.INFO, "Всё прошло успешно");
        }catch (SQLException e) {
            CRUDUtils.logger.log(Level.WARNING, "Произошла ошибка");
            throw new RuntimeException(e);
        }
        return deleteEmployees;
    }
}
