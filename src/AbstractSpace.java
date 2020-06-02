public class AbstractSpace implements  Space{
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

    @Override
    public String toString() {
        return "Парковочное место: "+person.toString()+"\t"+vehicle.toString();
    }

    public boolean stringEquals(String stateNumber) {
        return getVehicle().getStateNumber().equals(stateNumber);
    }
}
