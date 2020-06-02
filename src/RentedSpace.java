public class RentedSpace extends AbstractSpace implements Space, Cloneable {

    public RentedSpace() {
        super();
    }

    public RentedSpace(Person person) {
        super(person);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person,vehicle);
    }

    @Override
    public int hashCode() {
        return 53*super.hashCode();
    }

    @Override
    public String toString() {
        return "Tenant: " + super.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
