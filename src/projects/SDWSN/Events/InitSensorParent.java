package projects.SDWSN.Events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.Messages.InitControl;

public class InitSensorParent extends TimerEvent {
    @Override
    public void fire() {

        InitControl initControl = new InitControl(this.node, 0);
//        System.out.println("SubController ID " + this.node.getID() + "  created.");
        this.node.multicast(initControl);
    }
}
