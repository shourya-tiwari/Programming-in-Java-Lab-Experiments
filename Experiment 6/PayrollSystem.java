import java.util.*;

public class PayrollSystem {

    // 🔹 Safe Integer Input
    static int getInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("❌ Invalid input! Enter a number: ");
                sc.nextLine();
            }
        }
    }

    // 🔹 Safe Double Input
    static double getDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.print("❌ Invalid input! Enter a valid number: ");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n=========== EMPLOYEE PAYROLL SYSTEM ===========\n");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Contract Employee");
            System.out.println("3. Add Manager");
            System.out.println("4. Display All Employees");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("\nEnter choice: ");

            choice = getInt(sc);
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name1 = sc.nextLine();

                    System.out.print("Enter PAN: ");
                    String pan1 = sc.nextLine();

                    System.out.print("Enter Joining Date: ");
                    String date1 = sc.nextLine();

                    System.out.print("Enter Designation: ");
                    String des1 = sc.nextLine();

                    System.out.print("Enter ID: ");
                    int id1 = getInt(sc);

                    System.out.print("Enter Base Salary: ");
                    double bs1 = getDouble(sc);

                    System.out.print("Enter Bonus: ");
                    double bonus1 = getDouble(sc);

                    System.out.print("Enter Commission: ");
                    double comm1 = getDouble(sc);

                    sc.nextLine();

                    employees.add(new FullTimeEmployee(name1, pan1, date1, des1, id1, bs1, bonus1, comm1));
                    System.out.println("Full-Time Employee Added!");
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String name2 = sc.nextLine();

                    System.out.print("Enter PAN: ");
                    String pan2 = sc.nextLine();

                    System.out.print("Enter Joining Date: ");
                    String date2 = sc.nextLine();

                    System.out.print("Enter Designation: ");
                    String des2 = sc.nextLine();

                    System.out.print("Enter ID: ");
                    int id2 = getInt(sc);

                    System.out.print("Enter Hours Worked: ");
                    int hours = getInt(sc);

                    System.out.print("Enter Hourly Rate: ");
                    double rate = getDouble(sc);

                    sc.nextLine();

                    employees.add(new ContractEmployee(name2, pan2, date2, des2, id2, hours, rate));
                    System.out.println("Contract Employee Added!");
                    break;

                case 3:
                    System.out.print("Enter Name: ");
                    String name3 = sc.nextLine();

                    System.out.print("Enter PAN: ");
                    String pan3 = sc.nextLine();

                    System.out.print("Enter Joining Date: ");
                    String date3 = sc.nextLine();

                    System.out.print("Enter Designation: ");
                    String des3 = sc.nextLine();

                    System.out.print("Enter ID: ");
                    int id3 = getInt(sc);

                    System.out.print("Enter Base Salary: ");
                    double bs3 = getDouble(sc);

                    System.out.print("Enter Bonus: ");
                    double bonus3 = getDouble(sc);

                    System.out.print("Enter Commission: ");
                    double comm3 = getDouble(sc);

                    System.out.print("Enter Annual Allowance: ");
                    double allow = getDouble(sc);

                    sc.nextLine();

                    employees.add(new Manager(name3, pan3, date3, des3, id3, bs3, bonus3, comm3, allow));
                    System.out.println("Manager Added!");
                    break;

                case 4:
                    if (employees.isEmpty()) {
                        System.out.println("No employees found!");
                    } else {
                        double totalPayroll = 0;
                        int count = 1;

                        System.out.println("\n------------ EMPLOYEE DETAILS ------------\n");

                        for (Employee emp : employees) {
                            System.out.println("Employee #" + count++);
                            System.out.println();

                            emp.display();

                            double salary = emp.calcCTC();
                            System.out.printf("CTC         : %.2f\n", salary);
                            System.out.println();

                            totalPayroll += salary;
                        }

                        System.out.printf("===== TOTAL PAYROLL: %.2f =====\n", totalPayroll);
                        System.out.println("\n------------------------------------------------\n");
                    }
                    break;

                case 5:
                    if (employees.isEmpty()) {
                        System.out.println("No employees to delete!");
                        break;
                    }

                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = getInt(sc);

                    boolean found = false;

                    Iterator<Employee> it = employees.iterator();

                    while (it.hasNext()) {
                        Employee emp = it.next();
                        if (emp.empId == deleteId) {
                            it.remove();
                            System.out.println("Employee deleted successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Employee not found!");
                    }

                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}