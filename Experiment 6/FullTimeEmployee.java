class FullTimeEmployee extends Employee {
    protected double baseSalary, bonus, commission;

    public FullTimeEmployee(String name, String PANno, String joiningDate,
                            String designation, int empId,
                            double baseSalary, double bonus, double commission) {
        super(name, PANno, joiningDate, designation, empId);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.commission = commission;
    }

    @Override
    double calcCTC() {
        return baseSalary + bonus + commission;
    }
}