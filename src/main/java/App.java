import java.util.List;

public class App {
    public static  void main(String[] args){
        List<Student> students = CRUDUtils.getStudentDate("SELECT * FROM students;");
        System.out.println(students);


        Student student = new Student();
        student.setName("qweqwe");
        student.setSurname("dfgdfg");
        student.setCurt_name("QW");

        System.out.println(CRUDUtils.saveStudent(student));

        System.out.println(CRUDUtils.updateStudent(2, "JAVA"));

        System.out.println(CRUDUtils.deleteStudent(1));

    }
}
