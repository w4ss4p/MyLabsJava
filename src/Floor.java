public interface Floor {
    public boolean add(Space space);
    public boolean add(Space space, int index);
    public Space get(int index);
    public Space get(String stateNumber);
    public boolean contains(String stateNumber);
    public Space set(Space space, int index);
    public Space remove (int index);
    public Space remove (String stateNumber);
    public int size();
    public Space[] toArray();
    public Vehicle[] toVehicleArray();
    public Space[] getTypedSpaces(VehicleTypes type);
    public Space[] getFreeSpaces();
}
