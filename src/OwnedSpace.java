public class OwnedSpace extends AbstractSpace implements Space{

    public OwnedSpace(){
        super();
    }

    public OwnedSpace(Person person){
        super(person);
    }

    public OwnedSpace(Person person, Vehicle vehicle){
        super(person,vehicle);
    }

    @Override
    public int hashCode() {
        return 71 * super.hashCode();
    }

    @Override
    public String toString() {
        return "Owner: " + super.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


}
