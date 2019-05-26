package projects.SDWSN.events;

import jsensor.nodes.Node;
import jsensor.nodes.events.TimerEvent;
import jsensor.runtime.Jsensor;
import projects.SDWSN.nodes.Sensor;


public class VirtualSense extends TimerEvent {
    @Override
    public void fire() {
        Sensor sensor = (Sensor) this.node;
        this.node.unicast(sensor.onSense(), Jsensor.getNodeByID(sensor.getParent()));
    }

}
