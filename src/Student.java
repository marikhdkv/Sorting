public class Student
{
    private int id;
    private String name;
    private int age;
    private double mark;

    Student(String name, int age, double mark)
    {
        this.name = name;
        this.age = age;
        this.mark = mark;
        this.id = this.hashCode();
    }

    int getId() { return id; }
    void setId(int id) { this.id = id; }

    String getName() { return name; }
    int getAge() { return age; }
    double getMark() {return mark; }
}