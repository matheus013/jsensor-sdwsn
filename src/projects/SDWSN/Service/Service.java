package projects.SDWSN.Service;

public class Service {
    private ActionActuator actionActuator;
    private TypeSense typeSensor;
    private int actuator;

    public Service(ActionActuator actionActuator, TypeSense typeSensor, int actuator) {
        this.actionActuator = actionActuator;
        this.typeSensor = typeSensor;
        this.actuator = actuator;
    }

    public Service() {
    }

    public ActionActuator getActionActuator() {
        return actionActuator;
    }

    public Service setActionActuator(ActionActuator actionActuator) {
        this.actionActuator = actionActuator;
        return this;
    }

    public TypeSense getTypeSensor() {
        return typeSensor;
    }

    public Service setTypeSensor(TypeSense typeSensor) {
        this.typeSensor = typeSensor;
        return this;
    }

    public int getActuator() {
        return actuator;
    }

    public Service setActuator(int actuator) {
        this.actuator = actuator;
        return this;
    }
}
