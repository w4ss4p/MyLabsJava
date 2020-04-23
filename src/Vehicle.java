public class Vehicle {
    private String stateNumber;
    private String fabricator;
    private String model;

    public static final String DEFAULT_STATE_NUMBER = "";
    public static final String DEFAULT_FABRICATOR = "";
    public static final String DEFAULT_MODEL = "";
    //На всякий случай
    public static final Vehicle DEFAULT_VEHICLE = new Vehicle();

    public Vehicle() {
        //todo выносим поля по-умолчанию в константы - ИСПРАВИЛ!!!!!!!!!!
        /*setFabricator("");
        setStateNumber("");
        setModel("");*/
        this(DEFAULT_STATE_NUMBER,DEFAULT_FABRICATOR,DEFAULT_MODEL);
    }

    public Vehicle(String stateNumber, String fabricator, String model) {
        setStateNumber(stateNumber);
        setFabricator(fabricator);
        setModel(model);
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle: " + fabricator + "\t" + model + "\t Гос. номер - " + stateNumber;
    }
}
