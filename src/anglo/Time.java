package anglo;

/**
 * A Time class represents a Time value in the AngloSaxo Notation
 * it can be changed by adding a duration
 */
public class Time {

    private static final int MIN_HR_VALUE = 1;
    private static final int MAX_HR_VALUE = 12;
    private static final int MIN_MIN_VALUE = 0;
    private static final int MAX_MIN_VALUE = 59;
    private static final int MINUTES_IN_HOUR = 60;

    private int hr;
    private int min;

    /**
     * Enum Object to distinguish AM and PM
     */
    public enum Period {AM, PM}

    private Period p;

    /**
     * Constructs a time Object with given parameters
     * @param hr Hours in the AngloSaxo Notation
     * @param min Minutes in the AngloSaxo Notation
     * @param p AM or PM
     */
    public Time(int hr, int min, Period p) {
        if (hr < MIN_HR_VALUE || hr > MAX_HR_VALUE || min < MIN_MIN_VALUE || min > MAX_MIN_VALUE) {
            throw new IllegalArgumentException();
        } else {
            this.hr = hr;
            this.min = min;
            this.p = p;
        }
    }

    /**
     * Adds a Duration to current Time object
     * @param hr Hours to add
     * @param min Minutes to add
     * @return updated Time object
     */
    public Time addDuration(int hr, int min) {
        if (hr >= 2 * MAX_HR_VALUE || min > MAX_MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        int minutesOfThis = convertTimeToMinutes(this);
        minutesOfThis += hr * MINUTES_IN_HOUR + min;
        Time newTime = convertMinutesToTime(minutesOfThis);
        this.hr = newTime.hr;
        this.min = newTime.min;
        this.p = newTime.p;
        return this;
    }

    /**
     * Checks whether this Time object is before other
     * @param other compared Time object
     * @return boolean whether true or false
     */
    public boolean isBefore(Time other) {
        int thisInMinutes = convertTimeToMinutes(this);
        int otherInMinutes = convertTimeToMinutes(other);
        return thisInMinutes < otherInMinutes;
    }

    /**
     * Checks whether this Time object is after other
     * @param other compared Time object
     * @return boolean whether true or false
     */
    public boolean isAfter(Time other) {
        int thisInMinutes = convertTimeToMinutes(this);
        int otherInMinutes = convertTimeToMinutes(other);
        return thisInMinutes > otherInMinutes;
    }

    /**
     * Provides minutes of a Time object
     * @param time Time object to convert into minutes
     * @return minutes of Time object
     */
    public static int convertTimeToMinutes(Time time) {
        int hrForCalc = time.hr;
        if (time.hr == MAX_HR_VALUE) {
            hrForCalc -= MAX_HR_VALUE;
        }
        if (time.p == Period.PM) {
            hrForCalc += MAX_HR_VALUE;
        }
        return hrForCalc * MINUTES_IN_HOUR + time.min;
    }

    /**
     * returns a Time object of minutes given
     * @param min minutes to convert to
     * @return minutes of Time object
     */
    private static Time convertMinutesToTime(int min) {
        Time time;
        if (min >= MIN_MIN_VALUE && min <= MAX_MIN_VALUE) {
            time = new Time(12, min, Period.AM);
        } else if (min > MAX_MIN_VALUE && min < (MAX_HR_VALUE + 1) * MINUTES_IN_HOUR) {
            time = new Time(min / MINUTES_IN_HOUR, min % MINUTES_IN_HOUR, min < MINUTES_IN_HOUR * MAX_HR_VALUE ? Period.AM : Period.PM);
        } else if (min >= (MAX_HR_VALUE+1)*MINUTES_IN_HOUR) {
            time = new Time((min-MINUTES_IN_HOUR*MAX_HR_VALUE) / MINUTES_IN_HOUR, min % MINUTES_IN_HOUR, Period.PM);
        } else {
            throw new IllegalArgumentException();
        }
        return time;
    }

    /**
     * return string of Object
     * @return
     */
    @Override
    public String toString() {
        return this.hr + ":" + String.format("%02d", this.min) + " " + this.p;
    }
}