public class Space {
    private Person person;
    private Vehicle vehicle;

    public Space() {
        setPerson(Person.NON_NAMED_PERSON);
        setVehicle(new Vehicle());
    }

    public Space(Person person, Vehicle vehicle) {
        setPerson(person);
        setVehicle(vehicle);
    }

    public boolean isEmpty(){
        return vehicle == null || vehicle.getStateNumber().equals("");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Парковочное место: ");
        sb.append(person.toString()).append('\t');
        sb.append(vehicle.toString()).append('\t');
        return sb.toString();
    }

    public static void Swap(Space A, Space B){
        Space C = A;
        A = B;
        B = C;
    }
}
