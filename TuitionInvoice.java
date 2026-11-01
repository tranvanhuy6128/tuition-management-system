public class TuitionInvoice {
    private final String studentId;
    private final String fullName;
    private final String major;
    private final int credit;
    private final double totalPrice;
    private final boolean paid;

    public TuitionInvoice(Student student){
        this.studentId = student.getStudentId();
        this.fullName = student.getFullName();
        this.major = student.getMajor();
        this.credit = student.getCredit();
        this.totalPrice = student.calculateTuition();
        this.paid = student.isPaid();
    }

    public String getStudentId(){ return studentId; }
    public String getFullName(){ return fullName; }
    public String getMajor(){ return major; }
    public int getCredit(){ return credit; }
    public double getTotalPrice(){ return totalPrice; }
    public boolean isPaid(){ return paid; }

    public String getQrContent(){
        return "student_id=" + studentId + ";total_price =" + (long) totalPrice;
    }

    @Override
    public String toString(){
        return "Student ID: " + studentId + "\n" +
                "Student name: " + fullName + "\n" +
                "Major: " + major + "\n" +
                "Credit: " + credit + "\n" +
                "Total: " + (long) totalPrice + "\n" +
                "Status: " + (paid ? "PAID" : "UNPAID") + "\n" +
                "QR content: " + getQrContent();
    }
}
