public class AbstractSpace implements Cloneable, Space{
    private Person person;
    private Vehicle vehicle;

    protected AbstractSpace() {
        this(Person.NOT_NAMED_PERSON,Vehicle.NO_VEHICLE);
    }

    protected AbstractSpace(Person person){
        this(person,Vehicle.NO_VEHICLE);
    }

    protected AbstractSpace(Person person, Vehicle vehicle) {
        setPerson(person);
        setVehicle(vehicle);
    }

    public boolean isEmpty(){
        return vehicle == Vehicle.NO_VEHICLE || vehicle.getType() == VehicleTypes.NONE;
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

    public boolean stringEquals(String stateNumber) {
        return getVehicle().getStateNumber().equals(stateNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("<");
        sb.append(getPerson().toString()).append("> ТС: <");
        sb.append(getVehicle().toString()).append(">");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractSpace space = (AbstractSpace) obj;
        return  space.vehicle.equals(this.vehicle) &&
                space.person.equals(this.person);
    }

    @Override
    public int hashCode() {
        return person.hashCode() ^ vehicle.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        AbstractSpace space = (AbstractSpace) super.clone();
        space.setPerson((Person) this.person.clone());
        space.setVehicle((Vehicle) this.vehicle.clone());
        return space;
    }
}
