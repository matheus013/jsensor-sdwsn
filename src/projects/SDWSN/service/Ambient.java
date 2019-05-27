package projects.SDWSN.service;

public enum Ambient {
    A(0), B(1), C(2), D(3), E(5);

    private int value;

    Ambient(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
