package projects.SDWSN.events;

import jsensor.nodes.Node;
import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.nodes.Sensor;


public class VirtualSense extends TimerEvent {
    @Override
    public void fire() {
        Sensor sensor = (Sensor) this.node;
        this.node.multicast(sensor.onSense());
    }

}
