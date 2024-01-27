public class Doctor extends User {
    private String specialization;

    public Doctor(int id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
    }

    // Accessor (getter)
    public String getSpecialization() {
        return specialization;
    }

    // Mutator (setter)
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}
