public class Vehicle {
    private String stateNumber;
    private String fabricator;
    private String model;

    public Vehicle() {
        setStateNumber("");
        setFabricator("");
        setModel("");
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
        final StringBuilder sb = new StringBuilder("Vehicle: ");
        sb.append(fabricator).append("      \t");
        sb.append(model).append("      \t");
        sb.append("Гос. номер - ").append(stateNumber);
        return sb.toString();
    }
}
