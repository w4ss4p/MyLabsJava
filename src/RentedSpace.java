public class RentedSpace extends AbstractSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public RentedSpace() {
        super();
    }

    public RentedSpace(Person person) {
        super(person);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person,vehicle);
    }
}
