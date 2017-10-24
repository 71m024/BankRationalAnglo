package anglo;

/**
 * A class to test the Time class
 */
public class TimeTester {
    /**
     * Method to test the Time class
     * @param args not used
     */
    public static void main(String[] args) {
        Time time = new Time(12, 0, Time.Period.AM); //Create
        System.out.println(time);

        for (int i = 0; i < 24; i++) {
            System.out.println(time.addDuration(1, 0));
        }

        System.out.println("-----------");

        Time t0000 = new Time(12,0, Time.Period.AM);
        System.out.println("0000 " + t0000);
        Time t1000 = new Time(10,0, Time.Period.AM);
        System.out.println("1000 " + t1000);
        Time t1200 = new Time(12,0, Time.Period.PM);
        System.out.println("1200 "  + t1200);
        Time t1800 = new Time(6,0, Time.Period.PM);
        System.out.println("1800  "  + t1800);

        System.out.println(t0000.isBefore(t0000));//false
        System.out.println(t0000.isBefore(t1000));//true
        System.out.println(t1000.isBefore(t1200));//true
        System.out.println(t1200.isBefore(t1800));//true

        System.out.println("-----------");

        System.out.println(t1800.isAfter(t1800));
        System.out.println(t1800.isAfter(t1200));
        System.out.println(t1200.isAfter(t1000));
        System.out.println(t1000.isAfter(t0000));

    }
}