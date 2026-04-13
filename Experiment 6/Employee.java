abstract class Employee {
    protected String name, PANno, joiningDate, designation;
    protected int empId;

    public Employee(String name, String PANno, String joiningDate, String designation, int empId) {
        this.name = name;
        this.PANno = PANno;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    abstract double calcCTC();

    public void display() {
        System.out.printf("ID          : %d\n", empId);
        System.out.printf("Name        : %s\n", name);
        System.out.printf("Designation : %s\n", designation);
    }
    }