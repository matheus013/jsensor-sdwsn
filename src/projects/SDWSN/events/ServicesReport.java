package projects.SDWSN.events;

import jsensor.nodes.events.TimerEvent;
import jsensor.runtime.Jsensor;
import projects.SDWSN.messages.ServicesReportMessage;
import projects.SDWSN.nodes.Sensor;

public class ServicesReport extends TimerEvent {
    @Override
    public void fire() {
        ServicesReportMessage servicesReportMessage = new ServicesReportMessage(this.node);
        this.node.unicast(servicesReportMessage, Jsensor.getNodeByID(((Sensor) this.node).getParent()));
    }
}
