package projects.SDWSN.Nodes;

import jsensor.nodes.messages.Inbox;
import projects.SDWSN.Events.InitSensorParent;

import java.util.LinkedList;

public class SubController extends Device {
    LinkedList<Long> sensorsIDs;
    @Override
    public void handleMessages(Inbox inbox) {

    }


    @Override
    public void onCreation() {
        int time = 1;
        InitSensorParent ft = new InitSensorParent();
        ft.startRelative(time, this);
        ft.fire();
    }
}
