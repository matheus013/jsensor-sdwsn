package projects.SDWSN.Service;

public enum TypeSense {
    TEMPERATURE(1), LUMINOSITY(2), PRESSURE(3), HUMIDITY(4);

    private int value;

    TypeSense(int value) {
        this.value = value;
    }
}
