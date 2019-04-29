package projects.SDWSN.Events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.Nodes.Sensor;


public class VirtualSense extends TimerEvent {

    @Override
    public void fire() {
        Sensor sensor = (Sensor) this.node;
        this.node.multicast(sensor.onSense());
    }

}
