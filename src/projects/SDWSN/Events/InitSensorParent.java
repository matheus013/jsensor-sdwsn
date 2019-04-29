package projects.SDWSN.Events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.Messages.InitControl;

public class InitSensorParent extends TimerEvent {
    @Override
    public void fire() {

        InitControl initControl = new InitControl(this.node, 0);

        this.node.multicast(initControl);
    }
}
