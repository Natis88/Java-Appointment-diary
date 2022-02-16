
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Diary {
    /*
     * Diary is the meetings file that holds all the data and this class allow to
     * make changes on the file.
     */
    public Diary() {

    }

    public void load(String fname) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line = reader.readLine();

        while (line != null) {
            if (!(line.startsWith("null"))) {
                System.out.print(line + "\n");
            }
            line = reader.readLine();
        }
        reader.close();

    }

    public void save(String fname, Meeting[] meetsArr) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));

        for (int i = 0; i < meetsArr.length; i++) {

            writer.write(meetsArr[i] + "\n");

        }
        writer.close();
    }

    // public void addMetting(String fname, Meeting meet) throws IOException {
    // BufferedWriter writer = new BufferedWriter(new FileWriter(fname));

    // writer.write(meet + "\n");

    // writer.close();
    // }

    // public void findMetting(String fname, String subject, LocalDate start) throws
    // IOException, InterruptedException {
    // BufferedReader reader = new BufferedReader(new FileReader(fname));
    // String line = reader.readLine();
    // int count = 0;
    // while (line != null) {
    // try {
    // line = reader.readLine();

    // if (line.contains(subject) || line.contains(start.toString())) {
    // System.out.println("Result found: " + line);
    // Thread.sleep(2000);
    // count++;

    // }

    // } catch (NullPointerException e) {
    // continue;
    // }

    // }
    // if (count == 0) {
    // System.out.println("No results for this search");

    // }

    // reader.close();

    // }

    // public void removeMetting(String fname, String subject, LocalDate start)
    // throws IOException, InterruptedException {
    // BufferedReader reader = new BufferedReader(new FileReader(fname));
    // String line = reader.readLine();
    // PrintWriter pw = new PrintWriter(fname);
    // while (line != null) {
    // try {
    // line = reader.readLine();

    // if (line.contains(subject) && line.contains(start.toString())) {
    // pw.println(line);
    // pw.flush();
    // Thread.sleep(2000);
    // System.out.println("Meeting was found and removed successfully");
    // }
    // } catch (NullPointerException e) {
    // continue;
    // }

    // }
    // Thread.sleep(2000);
    // System.out.println("No such meeting");
    // reader.close();
    // pw.close();

    // }

}