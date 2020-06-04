import java.time.LocalDate;

public interface Floor {
    boolean add(Space space);
    boolean add(Space space, int index);
    Space get(int index);
    Space get(String stateNumber);
    boolean contains(String stateNumber);
    Space set(Space space, int index);
    Space remove (int index);
    Space remove (String stateNumber);
    boolean remove(Space space);
    int indexOf(Space space);
    public int countOfPersonsSpaces(Person person);
    int size();
    Space[] toArray();
    Vehicle[] toVehicleArray();
    Space[] getTypedSpaces(VehicleTypes type);
    Space[] getFreeSpaces();
    String toString();
    int hashCode();
    boolean equals(Object obj);
    public LocalDate nearestRentEndsDate() throws NoRentedSpaceException;
    public Space spaceWithNearestRentEndsDate() throws NoRentedSpaceException;
}
