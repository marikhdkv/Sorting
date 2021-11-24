import java.util.Comparator;

public class SortingStudentsByGPA implements Comparator<Student>
{
    private Student[] students;

    SortingStudentsByGPA(Student[] students)
    {
        this.students = students;
    }

    Student[] getStudents() { return students; }

    @Override
    public int compare(Student o1, Student o2)
    {
        return Double.compare(o1.getMark(), o2.getMark());
    }
}