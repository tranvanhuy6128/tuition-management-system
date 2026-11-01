public class RegularStudent extends Student{
    private static final double DISCOUNT_RATE = 1.0;

    public RegularStudent(String studentId, String fullName, String major, int credit){
        super(studentId, fullName, major, credit);
    }

    @Override
    public double calculateTuition(){
        return getCredit() * PRICE_PER_CREDIT * DISCOUNT_RATE;
    }
}
