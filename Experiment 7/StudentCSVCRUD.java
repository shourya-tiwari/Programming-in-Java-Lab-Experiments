import java.io.*;
import java.util.*;

public class StudentCSVCRUD {

    static String fileName = "Students.csv";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT CSV MENU =====");
            System.out.println("1. Create File");
            System.out.println("2. Add Initial Rows");
            System.out.println("3. Add Student (User Input)");
            System.out.println("4. Add 3 More Rows");
            System.out.println("5. Update Marks (by ID)");
            System.out.println("6. Calculate Percentage");
            System.out.println("7. Delete Row");
            System.out.println("8. Display File (Sorted + Tabular)");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1: createFile(); break;
                    case 2: addInitialRows(); break;
                    case 3: addStudentByUser(sc); break;
                    case 4: addMoreRows(); break;
                    case 5: updateMarks(sc); break;
                    case 6: calculatePercentage(); break;
                    case 7:
                        System.out.print("Enter row number to delete: ");
                        deleteRow(sc.nextInt());
                        break;
                    case 8: displayFormatted(); break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            }
        }
    }

    // CREATE FILE
    public static void createFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage");
        bw.newLine();
        bw.close();
        System.out.println("File created.");
    }

    // INITIAL DATA
    public static void addInitialRows() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write("1,Shourya,AIML,80,85,90,88,92,0");
        bw.newLine();
        bw.write("2,Riya,CS,75,70,72,78,80,0");
        bw.newLine();
        bw.close();
    }

    // USER INPUT
    public static void addStudentByUser(Scanner sc) throws IOException {
        sc.nextLine();

        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Branch: ");
        String branch = sc.nextLine();

        int[] m = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Marks" + (i + 1) + ": ");
            m[i] = sc.nextInt();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write(id + "," + name + "," + branch + "," +
                m[0] + "," + m[1] + "," + m[2] + "," +
                m[3] + "," + m[4] + ",0");
        bw.newLine();
        bw.close();
    }

    // ADD MORE
    public static void addMoreRows() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write("3,Aman,IT,65,70,75,0,0,0"); bw.newLine();
        bw.write("4,Neha,EXTC,60,62,64,0,0,0"); bw.newLine();
        bw.write("5,Rohit,MECH,55,58,60,0,0,0"); bw.newLine();
        bw.close();
    }

    // UPDATE MARKS BY ID
    public static void updateMarks(Scanner sc) throws IOException {
        List<String> lines = readAll();

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(lines.get(0)); bw.newLine();

        boolean found = false;

        for (int i = 1; i < lines.size(); i++) {
            String[] d = lines.get(i).split(",");

            if (Integer.parseInt(d[0]) == id) {
                found = true;
                System.out.println("Enter new marks:");

                for (int j = 0; j < 5; j++) {
                    System.out.print("Marks" + (j + 1) + ": ");
                    d[3 + j] = String.valueOf(sc.nextInt());
                }

                d[8] = "0"; // reset percentage
            }

            bw.write(String.join(",", d));
            bw.newLine();
        }

        bw.close();

        if (!found) throw new IOException("Student ID not found!");
    }

    // CALCULATE PERCENTAGE
    public static void calculatePercentage() throws IOException {
        List<String> lines = readAll();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(lines.get(0)); bw.newLine();

        for (int i = 1; i < lines.size(); i++) {
            String[] d = lines.get(i).split(",");

            int sum = 0;
            for (int j = 3; j <= 7; j++) {
                sum += Integer.parseInt(d[j]);
            }

            double percent = sum / 5.0;
            d[8] = String.format("%.2f", percent);

            bw.write(String.join(",", d));
            bw.newLine();
        }

        bw.close();
        System.out.println("Percentage updated.");
    }

    // DELETE
    public static void deleteRow(int row) throws IOException {
        List<String> lines = readAll();

        if (row <= 0 || row >= lines.size()) {
            throw new IOException("Invalid row!");
        }

        lines.remove(row);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (String l : lines) {
            bw.write(l); bw.newLine();
        }
        bw.close();
    }

    // READ
    public static List<String> readAll() throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }

    // DISPLAY TABULAR + SORTED
    public static void displayFormatted() throws IOException {
        List<String> lines = readAll();

        if (lines.size() <= 1) {
            System.out.println("No data.");
            return;
        }

        // remove header
        String header = lines.remove(0);

        // sort by studentId
        Collections.sort(lines, (a, b) -> {
            int id1 = Integer.parseInt(a.split(",")[0]);
            int id2 = Integer.parseInt(b.split(",")[0]);
            return id1 - id2;
        });

        System.out.println("\n-------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-10s %-8s %-6s %-6s %-6s %-6s %-6s %-10s\n",
                "ID", "Name", "Branch", "M1", "M2", "M3", "M4", "M5", "Percent");
        System.out.println("-------------------------------------------------------------------------------------");

        for (String line : lines) {
            String[] d = line.split(",");

            System.out.printf("%-5s %-10s %-8s %-6s %-6s %-6s %-6s %-6s %-10s\n",
                    d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8]);
        }

        System.out.println("-------------------------------------------------------------------------------------");
    }
}