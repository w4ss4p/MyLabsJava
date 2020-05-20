import java.util.Objects;

public class OwnedSpace implements Space{
    private Person person;
    private Vehicle vehicle;

    public OwnedSpace(Person person, Vehicle vehicle){
        setPerson(person);
        setVehicle(vehicle);
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = Objects.requireNonNull(person);
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = Objects.requireNonNull(vehicle);
    }

    @Override
    public boolean isEmpty() {
        return vehicle == null || vehicle.getStateNumber().equals("");
    }

    @Override
    public boolean stringEquals(String stateNumber) {
        return getVehicle().getStateNumber().equals(stateNumber);
    }
}
