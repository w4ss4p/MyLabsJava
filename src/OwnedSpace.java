public class OwnedSpace extends AbstractSpace implements Space{
    private Person person;
    private Vehicle vehicle;

    public OwnedSpace(){
        super();
    }

    public OwnedSpace(Person person){
        super(person);
    }

    public OwnedSpace(Person person, Vehicle vehicle){
        super(person,vehicle);
    }
}
