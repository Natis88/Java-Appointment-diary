
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import java.util.Scanner;

public class MeetingValidator {

    private LocalDateTime start;
    private LocalDateTime end;
    private String subject;
    private int numberCopies;
    private Frequency frequency;

    public MeetingValidator(String subject, LocalDateTime startTime, LocalDateTime endTime, Frequency freq,
            int copies) {

        this.subject = subject;
        this.start = startTime;
        this.end = endTime;
        this.frequency = freq;
        this.numberCopies = copies;

    }

    public MeetingValidator() {

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

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public MeetingValidator returnValidated(String subject, LocalDateTime startTime, LocalDateTime endTime,
            Frequency freq, int copies) {

        this.start = startTime;
        this.end = endTime;
        while (isOverlapping(getStart(), getEnd())) {
            takeInputFromUser(getStart(), getEnd());
        }
        checkWorkHours();
        this.start = getNearestHourQuarter(getStart());
        this.end = getNearestHourQuarter(getEnd());

        if (freq == Frequency.None) {
            copies = 0;
        } else if (copies == 0) {
            copies++;
        }

        this.subject = subject;
        this.frequency = freq;
        this.numberCopies = copies;
        return new MeetingValidator(this.subject, this.start, this.end, this.frequency, this.numberCopies);
    }

    /*
     * Get LocalDateTime object and check if the hour is hourQuarter if not it will
     * put the object to the nearest hour quarter
     */
    public static LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {

        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod);
        }

        newDatetime = newDatetime.truncatedTo(ChronoUnit.MINUTES);

        return newDatetime;
    }

    /* Function to check if the meetings hours are at range with work hours */
    public void checkWorkHours() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
        boolean hasChange = false;

        if (getStart().equals(getEnd())) {

            setEnd(getEnd().plusMinutes(15));
        }
        while (!validWorkHours(getStart(), getEnd())) {

            if (getStart().getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(getStart().toLocalDate() + " is Friday working hours are 8:00am-13:00pm");
            } else if (getStart().getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println("Could set meeting at " + getStart().toLocalDate() + " because its saturday");
                System.out.println("The meeting will be move to: " + getStart().toLocalDate().plusDays(1));
                setStart(getStart().plusDays(1));
                setEnd(getEnd().plusDays(1));
                hasChange = true;
                break;
            }

            takeInputFromUser(getStart(), getEnd());
            hasChange = true;

        }
        if (hasChange == false) {
            setStart(getStart());
            setEnd(getEnd());
        }

    }

    public boolean isOverlapping(LocalDateTime start, LocalDateTime end) {
        SortedDynamicArray sd = new SortedDynamicArray();
        Meeting[] meetArr = sd.getArray();

        for (Meeting meeting : meetArr) {
            try {
                if (start.isBefore(meeting.getEnd()) && end.isAfter(meeting.getStart())) {
                    System.out.println("There's already a meeting at these hours.");
                    return true;
                }
            }

            catch (NullPointerException e) {
                return false;
            }

        }

        return false;
    }

    /*
     * This function set 2 object of LDT to valid work hours by day and compare it
     * with the hours we get from input
     */
    public boolean validWorkHours(LocalDateTime start, LocalDateTime end) {
        LocalTime start4Check = null;

        LocalTime end4Check = null;

        String day = start.getDayOfWeek().name();

        switch (day.toLowerCase()) {
            case "sunday":
            case "monday":
            case "tuesday":
            case "wednesday":
            case "thursday":
                start4Check = LocalTime.of(8, 00, 00);
                end4Check = LocalTime.of(17, 00, 00);
                break;
            case "friday":
                start4Check = LocalTime.of(8, 00, 00);
                end4Check = LocalTime.of(13, 00, 00);
                break;

        }

        try {
            if (start.toLocalTime().isBefore(start4Check) || end.toLocalTime().isAfter(end4Check)) {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    private void takeInputFromUser(LocalDateTime startTime, LocalDateTime endTime) {
        try {
            Scanner sc = new Scanner(System.in);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
            System.out.print("Please enter meeting startTime yyyy-MM-dd,HH:mm: ");
            startTime = LocalDateTime.parse(sc.next(), formatter);
            System.out.print("Please enter meeting EndTime yyyy-MM-dd,HH:mm: ");
            endTime = LocalDateTime.parse(sc.next(), formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid LocalDateTime format ");

        }
        setStart(startTime);
        setEnd(endTime);
    }

}
