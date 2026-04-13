class Manager extends FullTimeEmployee {
    private double annualAllowance;

    public Manager(String name, String PANno, String joiningDate,
                   String designation, int empId,
                   double baseSalary, double bonus, double commission,
                   double annualAllowance) {
        super(name, PANno, joiningDate, designation, empId, baseSalary, bonus, commission);
        this.annualAllowance = annualAllowance;
    }

    @Override
    double calcCTC() {
        return baseSalary + bonus + annualAllowance;
    }
}