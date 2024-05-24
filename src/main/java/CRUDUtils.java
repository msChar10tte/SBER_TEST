import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, course_name) VALUES (?, ?, ?);";
    private static String UPDATE_STUDENT = "UPDATE students SET course_name = ? WHERE id =?;";
    private static String DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";
    public  static List<Student> getStudentDate(String quert){
        List<Student> students = new ArrayList<>();

        try(Connection connection =BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(quert)){

             ResultSet res = preparedStatement.executeQuery();

             while (res.next()){
                 long id = res.getLong("id");
                 String name= res.getString("name");
                 String surname= res.getString("surname");
                 String course_name= res.getString("course_name");

                 students.add(new Student(id, name ,surname, course_name));
             }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  students;
    }

    public  static List<Student> saveStudent(Student student){
        List<Student> students = new ArrayList<>();

        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT))
        {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCurt_name());

            preparedStatement.executeUpdate();


            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet res = allStudent.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name= res.getString("name");
                String surname= res.getString("surname");
                String course_name= res.getString("course_name");

                students.add(new Student(id, name ,surname, course_name));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static List<Student> updateStudent(long studentID, String courseName){
        List<Student> updateStudent = new ArrayList<>();

        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT))
        {

            preparedStatement.setString(1,courseName);
            preparedStatement.setLong(2,studentID);
            preparedStatement.executeUpdate();


            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet res = allStudent.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name= res.getString("name");
                String surname= res.getString("surname");
                String course_name= res.getString("course_name");

                updateStudent.add(new Student(id, name ,surname, course_name));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
return updateStudent;
    }

    public static  List<Student> deleteStudent(long studentID){
        List<Student> deleteStudent = new ArrayList<>();
        try(Connection connection = BDUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT))
        {

            preparedStatement.setLong(1,studentID);
            preparedStatement.executeUpdate();

            PreparedStatement allStudent = connection.prepareStatement("SELECT * FROM students");
            ResultSet res = allStudent.executeQuery();

            while (res.next()){
                long id = res.getLong("id");
                String name= res.getString("name");
                String surname= res.getString("surname");
                String course_name= res.getString("course_name");

                deleteStudent.add(new Student(id, name ,surname, course_name));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteStudent;
    }
}
