class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        String zeroHours = "";
        String zeroMinutes = "";
        String zeroSeconds = "";
        if (hours < 10) {
            zeroHours = "0";
        }
        if (minutes < 10) {
            zeroMinutes = "0";
        }
        if (seconds < 10) {
            zeroSeconds = "0";
        }

        return zeroHours + hours + ":" + zeroMinutes + minutes + ":" + zeroSeconds + seconds;
    }
}