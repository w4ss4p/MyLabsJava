public class Parking {
    private OwnerFloor[] ownerFloors;
    private int size;

    public Parking(int size) {
        this.size = size;
        ownerFloors = new OwnerFloor[size];
    }

    public Parking(OwnerFloor[] ownerFloors) {
        this.ownerFloors = new OwnerFloor[ownerFloors.length];
        size = ownerFloors.length;
    }

    public boolean add(OwnerFloor floor){
        if(checkFreeFloors()){
            lockAdd(floor);
            size++;
            return true;
        }else{
            extend();
            return false;
        }
    }

    public boolean add(OwnerFloor floor, int index){
        if(ownerFloors[index] == null){
            ownerFloors[index] = floor;
            size++;
            return true;
        }else{
            return false;
        }
    }

    public OwnerFloor get(int index){
        return ownerFloors[index];
    }

    public OwnerFloor set(OwnerFloor floor, int index){
        OwnerFloor buffer = ownerFloors[index];
        ownerFloors[index] = floor;
        if(floor == null && buffer!=null) size--;
        if(floor !=null && buffer == null) size++;
        return buffer;
    }

    public OwnerFloor delete(int index){
        OwnerFloor buf = ownerFloors[index];
        ownerFloors[index] = null;
        size--;
        return buf;
    }

    public int getSize() {
        return size;
    }

    public OwnerFloor[] getOwnerFloors() {
        trim();
        OwnerFloor[] buf = new OwnerFloor[size];
        for(int i = 0; i<size;i++){
            buf[i] = ownerFloors[i];
        }
        return buf;
    }

    public OwnerFloor[] getSortedFloors(){
        OwnerFloor[] buf = getOwnerFloors();
        for(int i = 0; i<buf.length;i++){
            for(int j = 0; j<buf.length-1;j++){
                if(buf[j].getSize()>buf[j+1].getSize()){
                    OwnerFloor.Swap(buf[j],buf[j+1]);
                }
            }
        }
        return buf;
    }

    public Vehicle[] getVehicles(){
        int vehiclesCount = 0;
        for(OwnerFloor floor: ownerFloors){
            vehiclesCount+= floor.getVehicles().length;
        }
        Vehicle[] forReturn = new Vehicle[vehiclesCount];
        for(OwnerFloor floor: ownerFloors){
            Tools.wiseAdd(floor.getVehicles(),forReturn);
        }
        return forReturn;
    }

    public Space getSpace(String stateNumber){
        for(OwnerFloor floor:ownerFloors){
            if(floor.get(stateNumber)!=null){
                return floor.get(stateNumber);
            }
        }
        return null;
    }

    public Space deleteSpace(String stateNumber){
        Space forReturn = getSpace(stateNumber);
        for(int i = 0; i<ownerFloors.length;i++){
            ownerFloors[i].delete(stateNumber);
        }
        return forReturn;
    }

    public Space setSpace(String stateNumber, Space space){
        Space forReturn = getSpace(stateNumber);
        for(int i = 0; i<ownerFloors.length;i++){
            ownerFloors[i].set(space,stateNumber);
        }
        return forReturn;
    }

    private void extend(){
        OwnerFloor[] newFloors = new OwnerFloor[ownerFloors.length*2];
        for(int i=0;i<ownerFloors.length;i++){
            newFloors[i] = ownerFloors[i];
        }
        ownerFloors = newFloors;
    }

    private boolean checkFreeFloors(){
        return !(size == ownerFloors.length);
    }

    private void lockAdd(OwnerFloor floor){
        for(int i = 0; i<ownerFloors.length;i++){
            if(ownerFloors[i] == null){
                ownerFloors[i] = floor;
            }
        }
    }

    private void trim(){
        for(int i = 0; i<ownerFloors.length;i++){
            for(int j = 0; j<ownerFloors.length-1;j++){
                if(ownerFloors[j]==null && ownerFloors[j+1]!=null){
                    OwnerFloor.Swap(ownerFloors[j],ownerFloors[j+1]);
                }
            }
        }
    }

}
