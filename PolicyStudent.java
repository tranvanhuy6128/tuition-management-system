public class PolicyStudent extends Student{
    private static final double DISCOUNT_RATE = 0.5;

    public PolicyStudent(String studentId, String fullName, String major, int credit){
        super(studentId, fullName, major, credit);
    }

    @Override
    public double calculateTuition(){
        return getCredit() * PRICE_PER_CREDIT * DISCOUNT_RATE;
    }
}
