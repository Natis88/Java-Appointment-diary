import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Menu {

    public void createMenu(SortedDynamicArray meets) throws InterruptedException, IOException {

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Set the day of month to 1
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // get day of week for 1st of month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // print month name and year
        System.out.println(new SimpleDateFormat("MMMM YYYY").format(calendar.getTime()));
        System.out.println(" S  M  T  W  T  F  S");

        // print initial spaces
        String initialSpace = "";
        for (int i = 0; i < dayOfWeek - 1; i++) {
            initialSpace += "   ";
        }
        System.out.print(initialSpace);

        // print the days of the month starting from 1
        for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
            for (int j = ((i == 0) ? dayOfWeek - 1 : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                System.out.printf("%2d ", dayOfMonth);
                dayOfMonth++;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("1.Add");
        System.out.println("2.Remove ");
        System.out.println("3.Find");
        System.out.println("4.Dailyschedule");
        System.out.println("5.WeekleySchedule");
        System.out.println("6.Save to file");
        System.out.println("7.Load from file");
        System.out.println("8.Exit");
        selectFromMenu(meets);

    }

    public void selectFromMenu(SortedDynamicArray meets) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        Diary di = new Diary();
        AppoitmentDiary diary = new AppoitmentDiary();
        while (choice != 7) {
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Set new meeting:");
                    diary.setMeeting(meets);
                    Thread.sleep(2000);
                    createMenu(meets);
                    break;
                case 2:
                    System.out.println("Remove meeting: ");
                    diary.removeMeeting(meets);
                    Thread.sleep(2000);
                    createMenu(meets);

                    break;
                case 3:
                    System.out.println("Searching for meetings...");
                    Thread.sleep(2000);
                    diary.findMeeting(meets);
                    Thread.sleep(2000);
                    createMenu(meets);
                    break;
                case 4:
                    new DailySchedule(meets.getArray());
                    createMenu(meets);

                    break;
                case 5:
                    new WeeklySchedule(meets.getArray());
                    createMenu(meets);
                    break;
                case 6:
                    try {
                        di.save("test", meets.getArray());
                    } catch (IOException e1) {
                        System.out.println("Faild to save");
                        return;
                    }
                    Thread.sleep(3000);
                    System.out.println("Diary Saved Successfully");
                    Thread.sleep(3000);
                    createMenu(meets);
                    break;
                case 7:
                    Thread.sleep(3000);
                    try {
                        di.load("test");
                        System.out.println();
                    } catch (IOException e) {
                        System.out.println("Could not allocate file name test");
                        return;
                    }

                    Thread.sleep(3000);
                    createMenu(meets);
                    break;

                case 8:
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}
