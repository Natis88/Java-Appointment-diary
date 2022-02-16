import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AppoitmentDiary { /* this class gets all the inputs from the user and does validation for it */

        public void setMeeting(SortedDynamicArray sd) throws InterruptedException {

                /* add meeting from input */

                // Scanner sc = new Scanner(System.in);
                // boolean isValid = false;
                // int copies = 0;
                // Frequency freq = Frequency.None;
                // boolean isRecurr = false;
                // LocalDateTime eStartTime = LocalDateTime.now();
                // LocalDateTime eDateTime = LocalDateTime.now();
                // Meeting meet;

                // DateTimeFormatter formatter =
                // DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
                // String subject = "";

                // System.out.print("Please enter subject: ");
                // subject = sc.next();
                // while (!isValid) {
                // try {

                // System.out.print("Please enter meeting start date yyyy-MM-dd,HH:mm: ");
                // eStartTime = LocalDateTime.parse(sc.next(), formatter);
                // isValid = true;

                // } catch (DateTimeParseException e) {
                // System.out.println("Invalid format try again");

                // }
                // }
                // isValid = false;
                // while (!isValid) {
                // try {

                // System.out.print("Please enter meeting end date yyyy-MM-dd,HH:mm: ");
                // eDateTime = LocalDateTime.parse(sc.next(), formatter);
                // isValid = true;

                // } catch (DateTimeParseException e) {
                // System.out.println("Invalid format try again");

                // }
                // }

                // isValid = false;

                // while (!isValid) {
                // try {
                // System.out.print("Is Recurring true/false : ");

                // isRecurr = sc.nextBoolean();
                // isValid = true;

                // } catch (InputMismatchException e) {
                // System.out.println("Invalid boolean value try true/false");
                // } catch (NoSuchElementException el) {
                // System.out.println("Something went wrong");
                // }

                // }

                // if (isValid == false) {
                // copies = 0;
                // freq = Frequency.None;
                // meet = new Meeting(subject, eStartTime, eDateTime, isRecurr, copies, freq);
                // } else {
                // isValid = false;
                // while (!isValid) {

                // try {
                // System.out.print("Please enter number of copies : ");

                // copies = sc.nextInt();
                // isValid = true;

                // } catch (InputMismatchException e) {
                // System.out.println("Maybe try numbers");
                // }

                // }
                // }
                // isValid = false;
                // System.out.println();

                // System.out.print("FREQUENCY" + "\n");
                // System.out.print("1.Daily" + "\n");
                // System.out.print("2.Weekley" + "\n");
                // System.out.print("3.Monthly" + "\n");
                // System.out.print("4.Annually" + "\n");
                // System.out.print("5.None" + "\n");
                // int freqq = 0;
                // while (!isValid) {
                // try {
                // System.out.print("Your choice 1-5: ");
                // freqq = sc.nextInt();
                // } catch (InputMismatchException e) {
                // System.out.println("Try 1-5");
                // continue;
                // }

                // switch (freqq) {
                // case 1:
                // freq = Frequency.Daily;
                // isValid = true;
                // break;
                // case 2:
                // freq = Frequency.Weekly;
                // isValid = true;
                // break;
                // case 3:
                // freq = Frequency.Monthly;
                // isValid = true;
                // break;
                // case 4:
                // freq = Frequency.Annually;
                // isValid = true;
                // break;
                // case 5:
                // freq = Frequency.None;
                // isValid = true;
                // break;

                // default:
                // continue;
                // }

                // }
                MeetingValidator mv = new MeetingValidator();

                Meeting meet = new Meeting(mv.returnValidated("sort", LocalDateTime.of(2021, 01, 03, 14, 00, 00),
                                LocalDateTime.of(2021, 01, 03, 14, 30, 00), Frequency.Daily, 10));
                Thread.sleep(2000);
                System.out.print("New meeting: " + meet + "\n");
                sd.add(meet);
                Thread.sleep(2000);
                System.out.println("Meeting has been set successfully");
                Meeting meet2 = new Meeting(mv.returnValidated("sort", LocalDateTime.of(2021, 01, 04, 14, 45, 00),
                                LocalDateTime.of(2021, 01, 04, 15, 00, 00), Frequency.None, 0));
                Thread.sleep(2000);
                System.out.print("New meeting: " + meet2 + "\n");
                sd.add(meet2);
                Thread.sleep(2000);
                System.out.println("Meeting has been set successfully");
                Meeting meet3 = new Meeting(mv.returnValidated("sort", LocalDateTime.of(2021, 01, 05, 15, 45, 00),
                                LocalDateTime.of(2021, 01, 05, 16, 00, 00), Frequency.None, 0));
                Thread.sleep(2000);
                System.out.print("New meeting: " + meet3 + "\n");
                sd.add(meet3);
                Thread.sleep(2000);
                System.out.println("Meeting has been set successfully");
                Meeting meet4 = new Meeting(mv.returnValidated("sort", LocalDateTime.of(2021, 01, 04, 12, 30, 00),
                                LocalDateTime.of(2021, 01, 04, 13, 00, 00), Frequency.Daily, 4));
                Thread.sleep(2000);
                System.out.print("New meeting: " + meet4 + "\n");
                sd.add(meet4);
                Thread.sleep(2000);
                System.out.println("Meeting has been set successfully");
        }

        public void removeMeeting(SortedDynamicArray sd) throws InterruptedException {

                /* remove meeting from input */

                // Scanner sc = new Scanner(System.in);
                // LocalDateTime eStartTime = LocalDateTime.now();
                // DateTimeFormatter formatter =
                // DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
                // boolean isValid = false;
                // System.out.print("Please enter meeting subject: ");
                // String sub = sc.next();
                // while (!isValid) {
                // try {

                // System.out.print("Please enter meeting start date yyyy-MM-dd,HH:mm: ");
                // eStartTime = LocalDateTime.parse(sc.next(), formatter);
                // isValid = true;

                // } catch (DateTimeParseException e) {
                // System.out.println("Invalid format try again");

                // }
                // }

                // sd.remove("sort", LocalDateTime.of(2020, 12, 13, 14, 00, 00));

        }

        public void findMeeting(SortedDynamicArray sd) throws InterruptedException {
                sd.find("sort", LocalDateTime.of(2020, 12, 13, 14, 00, 00));

        }

        public static void main(String[] args) throws IOException, InterruptedException {

                SortedDynamicArray sd = new SortedDynamicArray(10);
                Menu men = new Menu();
                men.createMenu(sd);

        }
}
