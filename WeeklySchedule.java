import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.Calendar;

class WeeklySchedule {

    Meeting[][] weekleyArr;

    private void PrintWeekley(Meeting[] meets, Meeting[][] weekleyArr) {
        System.out.println("Current week Schedule:" + "\n");
        weekleyArr = createWeekly(meets, weekleyArr);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        DateFormat df = new SimpleDateFormat("dd/MM");
        WeekFields weekFields = WeekFields.SUNDAY_START;
        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);

        for (int i = 0; i < Days.values().length - 1; i++) {
            System.out.print("   " + Days.values()[i].getDay() + "  " + df.format(c.getTime()) + "   ");
            c.add(Calendar.DATE, 1);
        }
        for (int row = 0; row < weekleyArr.length; row++) {
            System.out.println();
            for (int col = 0; col < weekleyArr[row].length; ++col) {
                try {
                    int weekNumber = weekleyArr[row][col].getStart().get(weekFields.weekOfWeekBasedYear());
                    if (weekNumber == weekOfYear) {
                        System.out.print("   " + weekleyArr[row][col].getStart().toLocalTime() + "-"
                                + weekleyArr[row][col].getEnd().toLocalTime() + "  ");
                    } else {
                        System.out.print("     -  " + "\t");
                    }

                } catch (NullPointerException e) {

                    System.out.print("     -  " + "\t");
                }

            }
            System.out.println();
        }

    }

    public WeeklySchedule(Meeting[] meets) {
        weekleyArr = new Meeting[meets.length][Days.values().length - 1];
        PrintWeekley(meets, weekleyArr);
    }

    private Meeting[][] createWeekly(Meeting[] meets, Meeting[][] weekleyArr) {
        Days day = Days.NONE;
        int correctRow = 0;
        for (int j = 0; j < meets.length; j++) {

            try {

                DayOfWeek dayofweek = meets[j].getStart().getDayOfWeek();
                while (dayofweek.name() != day.name()) {
                    day = day.next();
                }

                if (weekleyArr[correctRow][day.ordinal()] == null) {
                    weekleyArr[correctRow][day.ordinal()] = meets[j];
                }

                else {
                    while (weekleyArr[correctRow][day.ordinal()] != null) {
                        correctRow++;
                    }
                    weekleyArr[correctRow][day.ordinal()] = meets[j];

                }
            }

            catch (NullPointerException e) {
                break;
            }
            correctRow = 0;
        }
        return weekleyArr;

    }

}