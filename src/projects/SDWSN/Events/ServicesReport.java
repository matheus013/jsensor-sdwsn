package projects.SDWSN.Events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.Messages.ServicesReportMessage;
import projects.SDWSN.Nodes.Sensor;

public class ServicesReport extends TimerEvent {
    @Override
    public void fire() {
        ServicesReportMessage initControl = new ServicesReportMessage(this.node);

        this.node.multicast(initControl);
    }
}
