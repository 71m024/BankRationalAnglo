package anglo;

public class TimeTester {
    public static void main(String[] args) {
        Time t0000 = new Time(12, 0, Time.Period.AM);
        System.out.println(t0000);

        for (int i = 0; i < 24; i++) {
            System.out.println(t0000.addDuration(1, 0));
        }
    }
}