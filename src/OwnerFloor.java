public class OwnerFloor {
    private Space[] spaces;
    private int size;

    public static final int SIXTEEN = 16;
    public static final int EIGHT = 8;
    public static final int ZERO = 0;

    //todo числа выносятся в константы -----> ДОБАВИЛ THREE КОНСТАНТЫ!!!!!!!!!!
    public OwnerFloor() {
        spaces = new Space[SIXTEEN];
        size = ZERO;
    }

    public OwnerFloor(int size) {
        spaces = new Space[size];
        size = ZERO;
    }

    //todo конструктор не должен делать проверку массива, только инициализировать  -----> ПРОВЕРКА МАССИВА НУЖНА ДЛЯ
    //todo ТОГО ЧТОБЫ ИНИЦИАЛИЗИРОВАТЬ ПОЛЕ
    public OwnerFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        System.arraycopy(spaces, spaces.length, this.spaces, 0, spaces.length);
        size = checkPlacedSpaces(spaces);
    }

    private void extend(){
        //todo  используй System.arraycopy для копирования массива здесь и далее ------> ПЕРЕДЕЛАЛ!!!!!!!!
        Space[] newSpaces = new Space[spaces.length*2];
        System.arraycopy(spaces, spaces.length, newSpaces, 0, spaces.length);
        spaces = newSpaces;
    }

    private int checkPlacedSpaces(Space[] spaces){
        int count = 0;
        //todo  почему не for? -----> В СМЫСЛЕ?)))))
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
            //todo  логику сравнения вынести в отдельный метод -----> ЗАЧЕМ, ЕСЛИ ЕСТЬ EQUALS?
            if(spaces[i].stringEquals(stateNumber)){
                return spaces[i];
            }
        }
        return null;
    }

    public boolean hasSpace(String stateNumber){
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

    //todo  название remove подошло бы больше ---------> ИСПРАВИЛ!!!))))
    public Space remove(int index){
        Space forReturn = spaces[index];
        spaces[index] = null;
        size--;
        return forReturn;
    }

    public Space remove(String stateNumber){
        Space forReturn = get(stateNumber);
        for(int i = 0; i<spaces.length;i++){
            if(spaces[i].stringEquals(stateNumber)){
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
                    Space buf = spaces[j];
                    spaces[j] = spaces[j+1];
                    spaces[j+1] = buf;
                }
            }
        }
    }
    //todo названия переменных не информативны ------> ВЫПИЛИЛ ЭТОТ МЕТОД ГЫЫЫЫ
}
