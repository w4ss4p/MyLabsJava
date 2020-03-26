public class OwnerFloor {
    private Space[] spaces;
    private int size;

    public OwnerFloor() {
        spaces = new Space[16];
        size = 0;
    }

    public OwnerFloor(int size) {
        spaces = new Space[size];
        size = 0;
    }

    public OwnerFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        for(int i = 0; i<this.spaces.length;i++){
            this.spaces[i] = spaces[i];
        }
        size = checkPlacedSpaces(spaces);
    }

    private void extend(){
        Space[] newSpaces = new Space[spaces.length*2];
        for(int i=0;i<spaces.length;i++){
            newSpaces[i] = spaces[i];
        }
        spaces = newSpaces;
    }

    private int checkPlacedSpaces(Space[] spaces){
        int count = 0;
        for(Space space: spaces){
            if(!space.isEmpty())count++;
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
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].getVehicle().getStateNumber().equals(stateNumber)){
                return spaces[i];
            }
        }
        return null;
    }

    public boolean hasSpace(String stateNumber){
        for(int i = 0; i<spaces.length;i++){
            if(!spaces[i].isEmpty() && spaces[i].getVehicle().getStateNumber().equals(stateNumber)){
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
            if(spaces[i].getVehicle().getStateNumber().equals(stateNumber)){
                spaces[i] = space;
            }
        }
        if(space == null && forReturn!=null) size--;
        if(space !=null && forReturn == null) size++;
        return forReturn;
    }

    public Space delete(int index){
        Space forReturn = spaces[index];
        spaces[index] = null;
        size--;
        return forReturn;
    }

    public Space delete(String stateNumber){
        Space forReturn = get(stateNumber);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].getVehicle().getStateNumber().equals(stateNumber)){
                spaces[i] = null;
                return forReturn;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public Space[] getSpaces(){
        Space[] forReturn = new Space[size];
        trim();
        for(int i = 0; i<forReturn.length;i++){
            forReturn[i] = spaces[i];
        }
        return forReturn;
    }

    public Vehicle[] getVehicles(){
        Space[] spaces = getSpaces();
        Vehicle[] forReturn = new Vehicle[size];
        for(int i = 0; i<forReturn.length;i++){
            forReturn[i] = spaces[i].getVehicle();
        }
        return forReturn;
    }

    private void lockAdd(Space space){
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
                    Space.Swap(spaces[j],spaces[j+1]);
                }
            }
        }
    }

    public static void Swap(OwnerFloor A, OwnerFloor B){
        OwnerFloor C = A;
        A = B;
        B = C;
    }
}
