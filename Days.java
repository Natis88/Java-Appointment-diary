
public enum Days {

    SUNDAY("SUN"), MONDAY("MON"), TUESDAY("TUE"), WEDNESDAY("WED"), THURSDAY("THU"), FRIDAY("FRI"), SATURDAY("SAT"),
    NONE("NON");

    static final int NUMBER_OF_DAYS = SATURDAY.ordinal() + 1;

    private String day;

    public String getDay() {
        return this.day;
    }

    private Days(String day) {
        this.day = day;
    }

    public Days next() {
        return values()[(this.ordinal() + 1) % values().length];

    }

    @Override
    public String toString() {

        return this.name();
    }

}
