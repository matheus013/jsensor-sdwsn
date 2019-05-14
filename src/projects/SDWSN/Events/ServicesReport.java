package projects.SDWSN.Events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.Messages.ServicesReportMessage;
import projects.SDWSN.Nodes.Sensor;

public class ServicesReport extends TimerEvent {
    @Override
    public void fire() {
        ServicesReportMessage servicesReportMessage = new ServicesReportMessage(this.node);
//        System.out.println("Node ID " + this.node.getID() + " created.");
        this.node.multicast(servicesReportMessage);
    }
}
