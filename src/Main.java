public class Main
{
    //Задание №1
    public static void insertionSort(Student[] students) throws Exception
    {
        int sortedRangeEndIndex = 1;
        while (sortedRangeEndIndex < students.length)
        {
            if (students[sortedRangeEndIndex].getId() < students[sortedRangeEndIndex - 1].getId())
            {
                int insertIndex = findInsertionIndex(students, students[sortedRangeEndIndex].getId());
                insert(students, insertIndex, sortedRangeEndIndex);
            }
            ++sortedRangeEndIndex;
        }
    }

    private static int findInsertionIndex(Student[] students, int valueToInsert) throws Exception
    {
        for (int i = 0; i < students.length; ++i)
            if (students[i].getId() > valueToInsert)
                return i;

        throw new Exception("Индекс не найден");
    }

    private static void insert(Student[] students, int indexInsertingAt, int indexInsertingFrom)
    {
        Student temp = students[indexInsertingAt];

        students[indexInsertingAt] = students[indexInsertingFrom];

        if (indexInsertingFrom - indexInsertingAt >= 0)
            System.arraycopy(students, indexInsertingAt, students,
                    indexInsertingAt + 1, indexInsertingFrom - indexInsertingAt);

        students[indexInsertingAt + 1] = temp;
    }

    //Задание №2
    public static void quickSort(Student[] students)
    {
        quickSort(students, 0, students.length - 1);
    }

    private static void quickSort (Student[] students, int left, int right)
    {
        int i = left, j = right;
        Student pivot = students[(left + right) >> 1];
        SortingStudentsByGPA studentsByGPA = new SortingStudentsByGPA(students);

        while (i <= j)
        {
            while (studentsByGPA.compare(studentsByGPA.getStudents()[i], pivot) > 0)
                ++i;

            while (studentsByGPA.compare(studentsByGPA.getStudents()[j], pivot) < 0)
                --j;


            if (i <= j)
            {
                Student temp = students[i];
                students[i] = students[j];
                students[j] = temp;

                ++i;
                --j;
            }
        }

        // Recursive calls
        if (left < j)
        {
            quickSort(students, left, j);
        }

        if (i < right)
        {
            quickSort(students, i, right);
        }
    }

    // Задание №3
    public static Student[] unite(Student[] first, Student[] second)
    {
        Student[] students = new Student[first.length + second.length];
        System.arraycopy(first, 0, students, 0, first.length);
        System.arraycopy(second, 0, students, first.length, second.length);
        return students;
    }

    public static void MergeSort(Student[] items)
    {
        // Если массив содержит один элемент - прервать выполнение метода
        if (items.length <= 1)
            return;

        int leftSize = items.length / 2;
        int rightSize = items.length - leftSize;
        Student[] left = new Student[leftSize];
        Student[] right = new Student[rightSize];

        System.arraycopy(items, 0, left, 0, leftSize);
        System.arraycopy(items, leftSize, right, 0, rightSize);

        // Рекурсивное деление массива

        MergeSort(left);
        MergeSort(right);

        Merge(items, left, right);
    }

    private static void Merge(Student[] items, Student[] left, Student[] right)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        int targetIndex = 0;
        int remaining = left.length + right.length;     // общая длинна правой и левой части сортируемого массива

        while (remaining > 0)
        {
            if (leftIndex >= left.length)
                items[targetIndex] = right[rightIndex++];

            else if (rightIndex >= right.length)
                items[targetIndex] = left[leftIndex++];

            else if (left[leftIndex].getMark() < right[rightIndex].getMark())
                items[targetIndex] = left[leftIndex++];

            else
                items[targetIndex] = right[rightIndex++];

            targetIndex++;
            remaining--;
        }
    }

    public static void main(String[] args) throws Exception
    {
        //Задание №1
        Student[] iDNumber =
                {
                        new Student("Ilya", 18, 7.8),
                        new Student("Anastasiya", 19, 6.9),
                        new Student("Ivan", 19, 9.0),
                        new Student("Maxim", 18, 8.5),
                        new Student("Alyona", 19, 8.9),
                        new Student("Katya", 18, 8.2)
                };
        System.out.println("Задание №1");
        for (Student student : iDNumber)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId());


        insertionSort(iDNumber);

        System.out.println("\nSorted by id:");
        for (Student student : iDNumber)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId());

        //Задание №2
        Student[] students =
                {
                        new Student("Vladimir", 19, 6.8),
                        new Student("Dasha", 19, 7.3),
                        new Student("Peter", 18, 8.0),
                        new Student("Elena", 18, 9.5),
                        new Student("Liza", 19, 8.9),
                        new Student("Anton", 19, 6.6)
                };

        System.out.println("\nЗадание №2");
        for (Student student : students)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId() + ", Mark: " + student.getMark());

        quickSort(students);

        System.out.println("\nSorted by mark descending: ");
        for (Student student : students)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId() + ", Mark: " + student.getMark());

        //Задание №3
        System.out.println("\nЗадание №3");

        Student[] union = unite(iDNumber, students);

        for (Student student : union)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId() + ", Mark: " + student.getMark());

        MergeSort(union);

        System.out.println("\nSorted by mark ascending: ");
        for (Student student : union)
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId() + ", Mark: " + student.getMark());
    }
}