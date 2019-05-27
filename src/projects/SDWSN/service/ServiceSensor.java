package projects.SDWSN.service;

public class ServiceSensor implements Service<Double> {
    private Double value;
    private boolean status;
    private TypeSense typeSensor;
    private int actuator;
    private Ambient ambient;
    private Action action;
    private int duration;

    public ServiceSensor(TypeSense typeSensor, int actuator, Ambient ambient) {
        setActuator(actuator);
        setTypeSensor(typeSensor);
        setAmbient(ambient);
        setDuration(10);
    }

    public ServiceSensor(Ambient ambient, TypeSense typeSensor, Action action){
        setAmbient(ambient);
        setAction(action);
        setTypeSensor(typeSensor);
        setDuration(10);
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

    public Action getAction() {
        return action;
    }

    public ServiceSensor setAction(Action action) {
        this.action = action;
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

    public int getDuration() {
        return duration;
    }

    public ServiceSensor setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
