public final class Vehicle implements Cloneable{
    private String stateNumber;
    private String fabricator;
    private String model;
    private VehicleTypes type;

    public static final String DEFAULT_STATE_NUMBER = "";
    public static final String DEFAULT_FABRICATOR = "";
    public static final String DEFAULT_MODEL = "";
    public static final Vehicle NO_VEHICLE = new Vehicle(VehicleTypes.NONE);

    public Vehicle(VehicleTypes type) {
        this(DEFAULT_STATE_NUMBER,
                DEFAULT_FABRICATOR,DEFAULT_MODEL,type);
    }

    public Vehicle(String stateNumber, String fabricator, String model,VehicleTypes type) {
        setStateNumber(stateNumber);
        setFabricator(fabricator);
        setModel(model);
        setType(type);
    }

    public String getStateNumber() {
        return stateNumber;
    }

    private void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getFabricator() {
        return fabricator;
    }

    private void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public VehicleTypes getType() {
        return type;
    }

    private void setType(VehicleTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(getType() == VehicleTypes.NONE) return "NONE";
        final StringBuilder sb = new StringBuilder("<");
        sb.append(fabricator).append("> <");
        sb.append(model).append("> ");
        sb.append("(<").append(type).append(">) ");
        sb.append("Гос. номер - <").append(stateNumber).append('>');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return stateNumber.hashCode() ^
                fabricator.hashCode() ^
                model.hashCode() ^
                type.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vehicle vehicle = (Vehicle) object;
        return stateNumber.equals(vehicle.stateNumber) &&
                fabricator.equals(vehicle.fabricator) &&
                model.equals(vehicle.model) &&
                type == vehicle.type;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
