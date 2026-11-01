public abstract class Student {
    private final String studentId;
    private String fullName;
    private String major;
    private int credit;
    private boolean paid = false;

    public static final double PRICE_PER_CREDIT = 500000;

    public Student(String studentId, String fullName, String major, int credit){
        if (studentId == null || studentId.isBlank())
            throw new TuitionException("Student ID can't be empty!");
        this.studentId = studentId.trim();
        setFullName(fullName);
        setMajor(major);
        setCredit(credit);
    }

    public String getStudentId(){ return studentId; }
    public String getFullName(){ return fullName; }
    public String getMajor(){ return major; }
    public int getCredit(){ return credit; }
    public boolean isPaid(){ return paid; }

    public void setFullName(String fullName){
        if (fullName == null || fullName.isBlank())
            throw new TuitionException("Name can't be empty!");
        this.fullName = fullName.trim();
    }
    public void setMajor(String major){
        if (major == null || major.isBlank())
            throw new TuitionException("Major can't be empty!");
        this.major = major.trim();
    }
    public void setCredit(int credit){
        if (credit <= 0)
            throw new TuitionException("Credit must be greater than 0!");
        this.credit = credit;
    }
    public void paid(){ this.paid = true; }

    public abstract double calculateTuition();

    @Override
    public String toString(){
        return  "Student ID: " + studentId + "\n" +
                "Student name: " + fullName + "\n" +
                "Major: " + major + "\n" +
                "Credit: " + credit + "\n" +
                "Status: " + (paid ? "PAID" : "UNPAID");
    }
}
