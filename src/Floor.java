import java.time.LocalDate;

public interface Floor extends Comparable<Floor>,Iterable<Space> {
    boolean add(Space space);
    boolean add(Space space, int index);
    Space get(int index);
    Space set(Space space, int index);
    Space remove (int index);
    Space remove (String stateNumber);
    boolean remove(Space space);
    int indexOf(Space space);
    int size();
    Space[] toArray();
    Vehicle[] toVehicleArray();
    Space[] getTypedSpaces(VehicleTypes type);
    Space[] getFreeSpaces();
    String toString();
    int hashCode();
    boolean equals(Object obj);
    void checkRentedSpaces() throws NoRentedSpaceException;

    default Space spaceWithNearestRentEndsDate() throws NoRentedSpaceException{
        LocalDate date = nearestRentEndsDate();
        for(Space space:this){
            if(space instanceof RentedSpace){
                RentedSpace rs = (RentedSpace) space;
                if(rs.getRentEndsDate().equals(date)) return rs;
            }
        }
        return null;
    }
    default boolean contains(String stateNumber){
        for(Space space:this){
            if(space.stringEquals(stateNumber)) return true;
        }
        return false;
    }
    default Space get(String stateNumber){
        Utils.checkRegNumberFormat(stateNumber);
        for(Space space:this){
            if(space.stringEquals(stateNumber)) return space;
        }
        return null;
    }
    default int countOfPersonsSpaces(Person person){
        int count = 0;
        for(Space space:this){
            if(space.getPerson().equals(person)) count++;
        }
        return count;
    }
    default LocalDate nearestRentEndsDate() throws NoRentedSpaceException{
        checkRentedSpaces();
        LocalDate date = LocalDate.of(5000,0,0);
        for(Space space:this){
            if(space instanceof RentedSpace){
                RentedSpace rs = (RentedSpace) space;
                if(rs.getRentEndsDate().isBefore(date) &&
                        rs.getRentEndsDate().isAfter(LocalDate.now().minusDays(1))){}
                date = rs.getRentEndsDate();
            }
        }
        return date;
    }
}
