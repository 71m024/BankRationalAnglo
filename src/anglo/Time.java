package anglo;

public class Time {

    private static final int MIN_HR_VALUE = 1;
    private static final int MAX_HR_VALUE = 12;
    private static final int MIN_MIN_VALUE = 0;
    private static final int MAX_MIN_VALUE = 59;
    private static final int MINUTES_IN_HOUR = 60;

    private int hr;
    private int min;

    public enum Period {AM, PM}

    private Period p;

    public Time(int hr, int min, Period p) {
        if (hr < MIN_HR_VALUE || hr > MAX_HR_VALUE || min < MIN_MIN_VALUE || min > MAX_MIN_VALUE) {
            throw new IllegalArgumentException();
        } else {
            this.hr = hr;
            this.min = min;
            this.p = p;
        }
    }

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

//        this.hr += hr;
//        this.min += min;
//        if (this.min > MAX_MIN_VALUE) {
//            this.hr += this.min / (MAX_MIN_VALUE + 1);
//            this.min += this.min % (MAX_MIN_VALUE + 1);
//        }
//        if (this.hr > MAX_HR_VALUE) {
//            switch (this.p) {
//                case AM: {
//                    this.p = Period.PM;
//                    this.hr -= MAX_HR_VALUE;
//                }
//                case PM: {
//                    this.p = Period.AM;
//                    this.hr -= MAX_HR_VALUE;
//                }
//            }
//        }
//        return this;
    }

    public boolean isBefore(Time other) {
        int thisInMinutes = convertTimeToMinutes(this);
        int otherInMinutes = convertTimeToMinutes(other);
        return thisInMinutes < otherInMinutes;
    }

    public boolean isAfter(Time other) {
        int thisInMinutes = convertTimeToMinutes(this);
        int otherInMinutes = convertTimeToMinutes(other);
        return thisInMinutes > otherInMinutes;
    }

    private int convertTimeToMinutes(Time time) {
        int hrForCalc = time.hr;
        if (time.hr == MAX_HR_VALUE) {
            hrForCalc -= MAX_HR_VALUE;
        }
        if (time.p == Period.PM) {
            hrForCalc += MAX_HR_VALUE;
        }
        return hrForCalc * MINUTES_IN_HOUR + time.min;
    }

    private  Time convertMinutesToTime(int min) {
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

    @Override
    public String toString() {
        return this.hr + ":" + String.format("%02d", this.min) + " " + this.p;
    }
}