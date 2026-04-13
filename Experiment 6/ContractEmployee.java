class ContractEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public ContractEmployee(String name, String PANno, String joiningDate,
                            String designation, int empId,
                            int hoursWorked, double hourlyRate) {
        super(name, PANno, joiningDate, designation, empId);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calcCTC() {
        return hoursWorked * hourlyRate;
    }
}