package projects.SDWSN.events;

import jsensor.nodes.events.TimerEvent;
import projects.SDWSN.nodes.User;

public class RequestService extends TimerEvent {
    @Override
    public void fire() {
        User user = (User) this.node;
        this.node.unicast(user.onRequest(), user.getParent());
    }
}
