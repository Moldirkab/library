package modelsDraft;

public class StaffDraft extends UserDraft {
    private int salary;

    private StaffDraft(StaffBuilder builder) {
        super(builder);
        this.salary = builder.salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ", name: '" + getName() + "', surname: '" + getSurname() + "', salary: " + salary;
    }

    public static class StaffBuilder extends UserDraft.Builder {
        private int salary;

        public StaffBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        @Override
        public StaffDraft build() {
            return new StaffDraft(this);
        }
    }
}

