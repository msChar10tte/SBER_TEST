public class Student {
    private long id;
    private  String name;
    private  String surname;
    private  String course_name;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", curt_name='" + course_name + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(long id, String name, String surname, String curt_name) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course_name = curt_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurt_name() {
        return course_name;
    }

    public void setCurt_name(String curt_name) {
        this.course_name = curt_name;
    }
}
