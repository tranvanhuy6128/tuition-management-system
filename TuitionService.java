import java.util.ArrayList;
import java.util.List;

public class TuitionService{
    private final List<Student> students = new ArrayList<>();

    private Student findById(String studentId){
        for(Student student : students){
            if(student.getStudentId().equals(studentId))
                return student;
        }
        return null;
    }

    public Student getStudentById(String studentId){
        Student student = findById(studentId);
        if(student == null)
            throw new TuitionException("Can't find student: " + studentId);
        return student;
    }

    // 1. Display
    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }

    // 2. Add
    public void addStudent(Student student){
        if(student == null)
            throw new TuitionException("Student can't be null!");
        if(findById(student.getStudentId()) != null)
            throw new TuitionException("Student ID already exists!");
        students.add(student);
    }

    // 3. Update
    public void updateStudent(String studentId, String fullName, String major, int credit){
        Student student = getStudentById(studentId);
        student.setFullName(fullName);
        student.setMajor(major);
        student.setCredit(credit);
    }

    // 4. Delete
    public void deleteStudent(String studentId){
        students.remove(getStudentById(studentId));
    }

    // 5. Search
    public Student searchById(String studentId){
        return findById(studentId);
    }

    // 6. Invoice
    public TuitionInvoice createInvoice(String studentId){
        return new TuitionInvoice(getStudentById(studentId));
    }

    // 7. Pay
    public void payTuition(String studentId){
        Student student = getStudentById(studentId);
        if (student.isPaid())
            throw new TuitionException("Student already paid!");
        student.paid();
    }
}