import java.util.Objects;

public class Parking {
    private Floor[] floors;
    private int size;

    public Parking(int size) {
        this.size = size;
        floors = new Floor[size];
    }

    public Parking(Floor[] floors) {
        this.floors = new Floor[floors.length];
        size = floors.length;
    }

    public boolean add(Floor floor){
        if(checkFreeFloors()){
            lockAdd(floor);
            size++;
            return true;
        }else{
            extend();
            return false;
        }
    }

    public boolean add(Floor floor, int index){
        Objects.requireNonNull(floor);
        if(floors[index] == null){
            floors[index] = floor;
            size++;
            return true;
        }else{
            return false;
        }
    }

    public Floor get(int index){
        return floors[index];
    }

    public Floor set(Floor floor, int index){
        Objects.requireNonNull(floor);
        Floor buffer = floors[index];
        floors[index] = floor;
        if(floor == null && buffer!=null) size--;
        if(floor !=null && buffer == null) size++;
        return buffer;
    }

    public Floor delete(int index){
        Floor buf = floors[index];
        floors[index] = null;
        size--;
        return buf;
    }

    public int size() {
        return size;
    }

    public Floor[] toArray() {
        trim();
        Floor[] buf = new Floor[size];
        for(int i = 0; i<size;i++){
            buf[i] = floors[i];
        }
        return buf;
    }

    public Floor[] getSortedFloors(){
        Floor[] buf = toArray();
        for(int i = 0; i<buf.length;i++){
            for(int j = 0; j<buf.length-1;j++){
                if(buf[j].size()>buf[j+1].size()){
                    Floor bufFloor = buf[j];
                    buf[j] = buf[j+1];
                    buf[j+1] = bufFloor;
                }
            }
        }
        return buf;
    }

    public Vehicle[] getVehicles(){
        int vehiclesCount = 0;
        for(Floor floor: floors){
            vehiclesCount+= floor.toVehicleArray().length;
        }
        Vehicle[] forReturn = new Vehicle[vehiclesCount];
        for(Floor floor: floors){
            Tools.wiseAdd(floor.toVehicleArray(),forReturn);
        }
        return forReturn;
    }

    public Space getSpace(String stateNumber){
        Utils.checkRegNumberFormat(stateNumber);
        Objects.requireNonNull(stateNumber);
        for(Floor floor: floors){
            if(floor.get(stateNumber)!=null){
                return floor.get(stateNumber);
            }
        }
        return null;
    }

    public Space deleteSpace(String stateNumber){
        Utils.checkRegNumberFormat(stateNumber);
        Objects.requireNonNull(stateNumber);
        Space forReturn = getSpace(stateNumber);
        for(int i = 0; i< floors.length; i++){
            floors[i].remove(stateNumber);
        }
        return forReturn;
    }

    private void extend(){
        Floor[] newFloors = new Floor[floors.length*2];
        for(int i = 0; i< floors.length; i++){
            newFloors[i] = floors[i];
        }
        floors = newFloors;
    }

    private boolean checkFreeFloors(){
        return !(size == floors.length);
    }

    private void lockAdd(Floor floor){
        Objects.requireNonNull(floor);
        for(int i = 0; i< floors.length; i++){
            if(floors[i] == null){
                floors[i] = floor;
            }
        }
    }

    private void trim(){
        for(int i = 0; i< floors.length; i++){
            for(int j = 0; j< floors.length-1; j++){
                if(floors[j]==null && floors[j+1]!=null){
                    Floor bufFloor = floors[j];
                    floors[j] = floors[j+1];
                    floors[j+1] = bufFloor;
                }
            }
        }
    }

    public int getFreeSpaceCount(){
        int count = 0;
        for(int i = 0; i<size;i++){
            count+=floors[i].getFreeSpaces().length;
        }
        return count;
    }

    public int getTypedVehicleCount(VehicleTypes type){
        int count = 0;
        for(int i = 0; i<size;i++){
            count+=floors[i].getTypedSpaces(type).length;
        }
        return count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Floors(<");
        sb.append(size).append("> total):\n");
        for(int i = 0;i<size;i++){
            if(floors[i]!=null){
                sb.append(floors[i].toString());
            }else{
                sb.append("Пустой этаж");
            }
        }
        return sb.toString();
    }

    public Floor[] getFloorsWithPersonOnBoard(Person person){
        Objects.requireNonNull(person);
        int count = 0;
        for(int i = 0; i<size;i++){
            if(floors[i].countOfPersonsSpaces(person)>0) count++;
        }
        Floor[] forReturn = new Floor[count];
        int index = 0;
        for(int i = 0; i<size;i++){
            if(floors[i].countOfPersonsSpaces(person)>0){
                forReturn[index++] = floors[i];
            }
        }
        return forReturn;
    }
}
