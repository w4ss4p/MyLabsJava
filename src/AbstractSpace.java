import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class AbstractSpace implements Cloneable, Space{
    private Person person;
    private Vehicle vehicle;
    private LocalDate sinceDate;

    protected AbstractSpace() {
        this(Person.NOT_NAMED_PERSON,Vehicle.NO_VEHICLE, LocalDate.now());
    }

    protected AbstractSpace(Person person, LocalDate sinceDate){
        this(person,Vehicle.NO_VEHICLE, sinceDate);
    }

    protected AbstractSpace(Person person, Vehicle vehicle, LocalDate sinceDate) {
        setSinceDate(sinceDate);
        setPerson(person);
        setVehicle(vehicle);
    }

    @Override
    public Period period() {
        return Period.between(sinceDate,LocalDate.now());
    }

    @Override
    public LocalDate getSinceDate() {
        return sinceDate;
    }

    @Override
    public void setSinceDate(LocalDate sinceDate) {
        this.sinceDate = Objects.requireNonNull(sinceDate);
    }

    public boolean isEmpty(){
        return vehicle == Vehicle.NO_VEHICLE || vehicle.getType() == VehicleTypes.NONE;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = Objects.requireNonNull(person);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = Objects.requireNonNull(vehicle);
    }

    public boolean stringEquals(String stateNumber) {
        Utils.checkRegNumberFormat(stateNumber);
        return getVehicle().getStateNumber().equals(stateNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("<");
        sb.append(getPerson().toString()).append("> ТС: <");
        sb.append(getVehicle().toString()).append("> <");
        sb.append(sinceDate.toString()).append('>');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractSpace space = (AbstractSpace) obj;
        return  space.vehicle.equals(this.vehicle) &&
                space.person.equals(this.person) &&
                space.getSinceDate().equals(this.sinceDate);
    }

    @Override
    public int hashCode() {
        return person.hashCode() ^ vehicle.hashCode() ^ sinceDate.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        AbstractSpace space = (AbstractSpace) super.clone();
        space.setPerson((Person) this.person.clone());
        space.setVehicle((Vehicle) this.vehicle.clone());
        space.setSinceDate(LocalDate.of(sinceDate.getYear(),sinceDate.getMonth(),sinceDate.getDayOfMonth()));
        return space;
    }
}
