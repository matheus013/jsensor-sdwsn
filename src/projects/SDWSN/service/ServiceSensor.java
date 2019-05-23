package projects.SDWSN.service;

public class ServiceSensor implements Service<Double> {
    private Double value;
    private boolean status;
    private TypeSense typeSensor;
    private int actuator;
    private Ambient ambient;

    public ServiceSensor(TypeSense typeSensor, int id, Ambient ambient) {
        setActuator(id);
        setTypeSensor(typeSensor);
        setAmbient(ambient);
    }

    public TypeSense getTypeSensor() {
        return typeSensor;
    }

    public ServiceSensor setTypeSensor(TypeSense typeSensor) {
        this.typeSensor = typeSensor;
        return this;
    }

    public int getActuator() {
        return actuator;
    }

    public ServiceSensor setActuator(int actuator) {
        this.actuator = actuator;
        return this;
    }

    public Ambient getAmbient() {
        return ambient;
    }

    public ServiceSensor setAmbient(Ambient ambient) {
        this.ambient = ambient;
        return this;
    }

    @Override
    public void turnOn() {
        setStatus(true);
    }

    @Override
    public void turnOff() {
        setStatus(false);
    }

    @Override
    public void decrease() {
        this.setValue(this.value * 0.9);
    }

    @Override
    public void increase() {
        this.setValue(this.value * 1.1);
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public boolean isStatus() {
        return this.status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
