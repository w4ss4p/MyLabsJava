import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.Objects;

public class OwnerFloor implements Floor,Cloneable{
    private Space[] spaces;
    private int size;

    public static final int SIXTEEN = 16;
    public static final int EIGHT = 8;
    public static final int ZERO = 0;

    public OwnerFloor() {
        spaces = new Space[SIXTEEN];
        size = ZERO;
    }

    public OwnerFloor(int size) {
        spaces = new Space[size];
        size = ZERO;
    }

    public OwnerFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        System.arraycopy(spaces, spaces.length, this.spaces, 0, spaces.length);
        size = checkPlacedSpaces(spaces);
    }

    private void extend(){
        Space[] newSpaces = new Space[spaces.length*2];
        System.arraycopy(spaces, spaces.length, newSpaces, 0, spaces.length);
        spaces = newSpaces;
    }

    private int checkPlacedSpaces(Space[] spaces){
        int count = 0;
        for(Space space: spaces){
            if(space != null && !space.isEmpty())count++;
        }
        return count;
    }

    public boolean add(Space space){
        if(!checkFreeSpaces()){
            extend();
            return false;
        }else{
            lockAdd(space);
            size++;
            return true;
        }
    }

    public boolean add(Space space, int number){
        Objects.requireNonNull(space);
        if(spaces[number].isEmpty()){
            spaces[number] = space;
            size++;
            return true;
        }else{
            return false;
        }
    }

    public Space get(int number){

        return spaces[number];
    }

    public Space get(String stateNumber){
        Utils.checkRegNumberFormat(stateNumber);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].stringEquals(stateNumber)){
                return spaces[i];
            }
        }
        return null;
    }

    public boolean contains(String stateNumber){
        for(int i = 0; i<spaces.length;i++){
            if(!spaces[i].isEmpty() && spaces[i].stringEquals(stateNumber)){
                return true;
            }
        }
        return false;
    }

    public Space set(Space space, int index){
        Space forReturn = spaces[index];
        spaces[index] = space;
        if(space == null && forReturn!=null) size--;
        if(space !=null && forReturn == null) size++;
        return forReturn;
    }

    public Space set(Space space, String stateNumber){
        Space forReturn = get(stateNumber);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].stringEquals(stateNumber)){
                spaces[i] = space;
            }
        }
        if(space == null && forReturn!=null) size--;
        if(space !=null && forReturn == null) size++;
        return forReturn;
    }

    public Space remove(int index){
        Space forReturn = spaces[index];
        spaces[index] = null;
        size--;
        return forReturn;
    }

    public Space remove(String stateNumber){
        Utils.checkRegNumberFormat(stateNumber);
        Space forReturn = get(stateNumber);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].stringEquals(stateNumber)){
                spaces[i] = null;
                return forReturn;
            }
        }
        return null;
    }

    public Space[] toArray(){
        Space[] forReturn = new Space[size];
        trim();
        for(int i = 0; i<forReturn.length;i++){
            forReturn[i] = spaces[i];
        }
        return forReturn;
    }

    public Vehicle[] toVehicleArray(){
        Space[] spaces = toArray();
        Vehicle[] forReturn = new Vehicle[size];
        for(int i = 0; i<forReturn.length;i++){
            forReturn[i] = spaces[i].getVehicle();
        }
        return forReturn;
    }

    private void lockAdd(Space space){
        Objects.requireNonNull(space);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].isEmpty()){
                spaces[i] = space;
                return;
            }
        }
    }

    private boolean checkFreeSpaces(){
        return !(size == spaces.length);
    }

    private void trim(){
        for(int i = 0; i<spaces.length;i++){
            for(int j = 0; j<spaces.length-1;j++){
                if(spaces[j]==null && spaces[j+1]!=null){
                    Space buf = spaces[j];
                    spaces[j] = spaces[j+1];
                    spaces[j+1] = buf;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Space[] getTypedSpaces(VehicleTypes type) {
        int count = 0;
        for(int i = 0; i<size;i++){
            if(spaces[i].getVehicle().getType() == type) count++;
        }
        Space[] forReturn = new Space[count];
        count = 0;
        for(int i = 0; i<size;i++){
            if(spaces[i].getVehicle().getType() == type) forReturn[count++] = spaces[i];
        }
        return forReturn;
    }

    @Override
    public Space[] getFreeSpaces() {
        int count = 0;
        for(int i = 0; i<size;i++){
            if(spaces[i].isEmpty()) count++;
        }
        Space[] forReturn = new Space[count];
        count = 0;
        for(int i = 0; i<size;i++){
            if(spaces[i].isEmpty()) forReturn[count++] = spaces[i];
        }
        return forReturn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rented spaces: \n");
        for(int i = 0; i<size;i++){
            if(spaces[i]!=null) sb.append(spaces[i].toString()).append('\n');
            else{
                sb.append("--------------------");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OwnerFloor that = (OwnerFloor) obj;
        boolean answer = that.size == this.size;
        if(answer){
            for(int i = 0; i<size;i++){
                answer &= (that.toArray()[i].equals(this.toArray()[i]));
            }
        }
        return answer;
    }

    @Override
    public int hashCode() {
        int hash = 71 * size;
        for(int i = 0; i<size;i++){
            if(spaces[i]!=null)
                hash*=spaces[i].hashCode();
        }
        return hash;
    }

    public Object clone() throws CloneNotSupportedException{
        return new OwnerFloor(this.spaces.clone());
    }

    @Override
    public boolean remove(Space space) {
        Objects.requireNonNull(space);
        for(int i = 0; i<size;i++){
            if(spaces[i].equals(space)){
                spaces[i] = null;
                trim();
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Space space) {
        for(int i = 0; i<size;i++){
            if(spaces[i].equals(space)) return i;
        }
        return -1;
    }

    @Override
    public int countOfPersonsSpaces(Person person) {
        int count = 0;
        for(int i = 0;i<size;i++){
            if(spaces[i].getPerson().equals(person)) count++;
        }
        return count;
    }

    @Override
    public LocalDate nearestRentEndsDate() throws NoRentedSpaceException{
        checkRentedSpaces();
        LocalDate date = LocalDate.of(5000,0,0);
        for(int i = 0;i<spaces.length;i++){
            if(spaces[i] instanceof RentedSpace){
                RentedSpace rs = (RentedSpace) spaces[i];
                if(rs.getRentEndsDate().isBefore(date) &&
                        rs.getRentEndsDate().isAfter(LocalDate.now().minusDays(1))){}
                date = rs.getRentEndsDate();
            }
        }
        return date;
    }

    @Override
    public Space spaceWithNearestRentEndsDate() throws NoRentedSpaceException{
        LocalDate date = nearestRentEndsDate();
        for(int i = 0;i<spaces.length;i++){
            if(spaces[i] instanceof RentedSpace){
                RentedSpace rs = (RentedSpace) spaces[i];
                if(rs.getRentEndsDate().equals(date)) return rs;
            }
        }
        return null;
    }


    private void checkRentedSpaces() throws NoRentedSpaceException{
        int rentedSpaceCount = 0;
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i] instanceof RentedSpace) rentedSpaceCount++;
        }
        if(rentedSpaceCount==0) throw new NoRentedSpaceException();
    }
}
