package projects.SDWSN.events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.messages.Request;

public class OrcRequestService extends TimerEvent {

    private Request request;

    private Request getRequest() {
        return request;
    }

    public OrcRequestService setRequest(Request request) {
        this.request = request;
        return this;
    }

    @Override
    public void fire() {
        Request request = getRequest();
        this.node.unicast(request, request.getSlaveNode());
    }
}
