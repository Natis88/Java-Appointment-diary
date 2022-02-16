
import java.time.LocalDateTime;

class Meeting implements Comparable<Meeting> { /* This class manage all the objects of the meeting */

    private LocalDateTime start;
    private LocalDateTime end;
    private String subject;
    private int numberCopies;
    private Frequency frequency;

    /* Create meeting validator class */
    public Meeting(MeetingValidator meet) {

        this.start = meet.getStart();
        this.end = meet.getEnd();
        this.numberCopies = meet.getCopies();
        this.frequency = meet.getFrequency();
        this.subject = meet.getSubject();
    }

    public Meeting(String subject, LocalDateTime startTime, LocalDateTime endTime, Frequency freq, int copies) {
        this.start = startTime;
        this.end = endTime;
        this.numberCopies = copies;
        this.frequency = freq;
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public int getCopies() {
        return this.numberCopies;
    }

    public Frequency getFrequency() {
        return this.frequency;
    }

    @Override
    public int compareTo(Meeting meet) {
        return this.start.compareTo(meet.start);
    }

    @Override
    public boolean equals(Object obj) {

        return this.start.equals(obj);
    }

    @Override
    public String toString() {

        return "Start: " + this.start.toLocalDate() + "," + this.start.toLocalTime() + " End: " + this.end.toLocalDate()
                + "," + this.end.toLocalTime() + " Subject: " + subject + " Copies: " + numberCopies + " Frequency: "
                + frequency;
    }

}