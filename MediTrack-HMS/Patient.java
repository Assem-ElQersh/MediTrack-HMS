public class Patient extends User {
    private String condition;

    public Patient(int id, String name, int age, String condition) {
        super(id, name, age);
        this.condition = condition;
    }

    // Accessor (getter)
    public String getCondition() {
        return condition;
    }

    // Mutator (setter)
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return super.toString() + ", Condition: " + condition;
    }
}
