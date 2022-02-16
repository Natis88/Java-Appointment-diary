public enum Frequency {

    None, Daily, Weekly, Monthly, Annually,;

    static final int NUMBER_OF_FREQUENCY = Annually.ordinal() + 1;

    @Override
    public String toString() {

        return this.name();
    }
}
