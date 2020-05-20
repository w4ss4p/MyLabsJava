public class RentedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public RentedSpace() {
        setPerson(Person.NON_NAMED_PERSON);
        setVehicle(new Vehicle());
    }

    public RentedSpace(Person person, Vehicle vehicle) {
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
        return "Парковочное место: "+person.toString()+"\t"+vehicle.toString();
    }
    
    public boolean stringEquals(String stateNumber) {
        return getVehicle().getStateNumber().equals(stateNumber);
    }
}
