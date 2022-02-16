import java.time.LocalDate;
import java.util.Calendar;

public class DailySchedule {

    public DailySchedule(Meeting[] meets) {

        printDaily(meets);

    }

    private void printDaily(Meeting[] meet) {
        Calendar cl = Calendar.getInstance();
        boolean found = false;
        System.out.println("Todays Meetings:");
        if (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            System.out.println("No meetings in Saturday");
            return;
        }
        for (Meeting meeting : meet) {

            try {
                if (meeting.getStart().toLocalDate().isEqual(LocalDate.now()))
                    System.out.println(meeting);
                found = true;
            } catch (NullPointerException e) {
                continue;
            }

        }
        if (!found) {
            System.out.println("No meetings for today");
            return;
        }
    }

}