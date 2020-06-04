import java.time.LocalDate;
import java.time.Period;

public interface Space {
    public Person getPerson();
    public void setPerson(Person person);
    public Vehicle getVehicle();
    public void setVehicle(Vehicle vehicle);
    public boolean isEmpty();
    public boolean stringEquals(String stateNumber);
    public LocalDate getSinceDate();
    public void setSinceDate(LocalDate date);
    public Period period();
    }
