import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class RentedSpace extends AbstractSpace implements Space, Cloneable {
    private LocalDate rentEndsDate;

    public RentedSpace() {
        super();
        this.rentEndsDate = LocalDate.now().plusDays(1);
    }

    public RentedSpace(Person person,LocalDate sinceDate, LocalDate rentEndsDate) {
        this(person,Vehicle.NO_VEHICLE,sinceDate,rentEndsDate);
    }

    public RentedSpace(Person person, Vehicle vehicle, LocalDate sinceDate, LocalDate rentEndsDate) {
        super(person,vehicle,sinceDate);
        this.rentEndsDate = rentEndsDate;
    }

    public LocalDate getRentEndsDate() {
        return rentEndsDate;
    }

    public void setRentEndsDate(LocalDate rentEndsDate) {
        this.rentEndsDate = Objects.requireNonNull(rentEndsDate);
    }

    public Period getRemainingPerido(){
        return Period.between(LocalDate.now(),rentEndsDate);
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
