
import java.time.LocalDateTime;
import java.util.Arrays;

class SortedDynamicArray {
    private final static int DEFAULT_CAPACITY = 10;
    private Meeting[] source;
    private int capacity;

    public SortedDynamicArray(int capacity) {
        this.capacity = capacity;
        this.source = new Meeting[capacity];

    }

    public SortedDynamicArray() {

        this.source = new Meeting[DEFAULT_CAPACITY];

    }

    public int getCapacity() {
        return this.capacity;
    }

    public Meeting[] getArray() {
        return this.source;
    }

    public void add(Meeting meet) {

        if (meet.getFrequency() != Frequency.None) {

            addMeetingByFrequency(meet);

        } else {
            findPositionAndInsert(source, meet);

        }

    }

    /* The main function of insertion sort using arraycopy */
    private void findPositionAndInsert(Meeting[] source, Meeting meet) {

        Meeting[] dest = new Meeting[source.length + 1];

        for (int i = 0; i < source.length; i++) {
            if (source[i] != null)

            {

                if (source[i].compareTo(meet) < 0) {
                    continue;
                } else if (source[i].compareTo(meet) > 0) {
                    System.arraycopy(source, 0, dest, 0, i);
                    dest[i] = meet;
                    System.arraycopy(source, i, dest, i + 1, source.length - i);
                    this.source = Arrays.copyOf(dest, dest.length);
                    break;
                }
            } else
                this.source[i] = meet;
            break;
        }
    }

    /* function gets meeting object use also equals to check meeting object */
    public boolean remove(Meeting meet) throws InterruptedException {

        Meeting[] dest = new Meeting[source.length - 1];

        for (int i = 0; i < source.length; i++) {
            try {

                if (source[i].equals(meet)) {

                    System.out.print("Found match: " + source[i] + "\n");
                    System.arraycopy(source, 0, dest, 0, i);
                    System.arraycopy(source, i + 1, dest, i, dest.length - i);
                    this.source = Arrays.copyOf(dest, dest.length);
                    Thread.sleep(1000);
                    System.out.println("Meeting was removed successfully");
                    return true;
                }
            } catch (NullPointerException e) {
                break;
            }

        }
        System.out.println("No such meeting has found");
        return false;
    }

    /* Create equals function and send meeting to find,find gets meeting object */
    public boolean find(String subject, LocalDateTime dt) {
        for (int i = 0; i < source.length; i++) {
            try {
                if (source[i].getSubject().equals(subject) && source[i].getStart().equals(dt)) {
                    System.out.print("Meeting found: " + source[i] + "\n");
                    return true;
                }
            } catch (NullPointerException e) {
                System.out.println("No meeting found");
                return false;
            }

        }
        return false;

    }

    /*
     * If the meeting has frequency it will make copy of the meeting *number of
     * copies
     */
    private void addMeetingByFrequency(Meeting meet) {

        MeetingValidator mv = new MeetingValidator();
        Frequency freq = meet.getFrequency();
        int countInsert = 0;
        Meeting original = meet;
        UpdatedMeetings up2date = new UpdatedMeetings(original, meet);

        while (countInsert < meet.getCopies()) {
            switch (freq) {
                case Daily:

                    up2date = add(up2date.updated, up2date.original, mv, freq);
                    up2date = new UpdatedMeetings(up2date.original, up2date.updated);

                    countInsert++;
                    break;
                case Weekly:

                    up2date = add(up2date.updated, up2date.original, mv, freq);
                    up2date = new UpdatedMeetings(up2date.original, up2date.updated);
                    countInsert++;
                    break;
                case Monthly:

                    up2date = add(up2date.updated, up2date.original, mv, freq);
                    up2date = new UpdatedMeetings(up2date.original, up2date.updated);
                    countInsert++;
                    break;
                case Annually:
                    up2date = add(up2date.updated, up2date.original, mv, freq);
                    up2date = new UpdatedMeetings(up2date.original, up2date.updated);
                    countInsert++;
                    break;
                default:
                    break;
            }

        }

    }

    private static Meeting IncraseDay(Meeting meet, Frequency frequency) {
        LocalDateTime start = meet.getStart();
        LocalDateTime end = meet.getEnd();

        switch (frequency) {
            case Daily:
                start = meet.getStart().plusDays(1);
                end = meet.getEnd().plusDays(1);
                break;
            case Weekly:
                start = meet.getStart().plusWeeks(1);
                end = meet.getEnd().plusWeeks(1);
                break;
            case Monthly:
                start = meet.getStart().plusMonths(1);
                end = meet.getEnd().plusMonths(1);
                break;
            case Annually:
                start = meet.getStart().plusYears(1);
                end = meet.getEnd().plusYears(1);
                break;

            default:
                break;
        }

        return new Meeting(meet.getSubject(), start, end, meet.getFrequency(), meet.getCopies());

    }

    private UpdatedMeetings add(Meeting meet, Meeting original, MeetingValidator mv, Frequency freq) {
        meet = new Meeting(
                mv.returnValidated(meet.getSubject(), meet.getStart(), meet.getEnd(), freq, meet.getCopies()));

        if (!(meet.getStart().equals(original.getStart()))) {
            findPositionAndInsert(source, meet);
            original = IncraseDay(original, freq);
            meet = IncraseDay(original, freq);

        } else {
            findPositionAndInsert(source, meet);
            meet = IncraseDay(meet, freq);
            original = meet;
        }
        return new UpdatedMeetings(original, meet);
    }

    private class UpdatedMeetings {
        private Meeting original;
        private Meeting updated;

        public UpdatedMeetings(Meeting oriMeeting, Meeting upMeeting) {
            this.original = oriMeeting;
            this.updated = upMeeting;
        }
    }

}
