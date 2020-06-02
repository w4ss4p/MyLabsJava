public final class Vehicle {
    private String stateNumber;
    private String fabricator;
    private String model;
    private VehicleTypes type;

    public static final String DEFAULT_STATE_NUMBER = "";
    public static final String DEFAULT_FABRICATOR = "";
    public static final String DEFAULT_MODEL = "";
    //На всякий случай
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
        return "Vehicle: " + fabricator + "\t" + model + "\t Гос. номер - " + stateNumber;
    }
}
