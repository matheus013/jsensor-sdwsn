package projects.SDWSN.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import projects.FailDetect.Timers.FloodingTimer;

import java.util.LinkedList;

public class Device extends Node {
    protected LinkedList<Long> messagesIDs;

    @Override
    public void handleMessages(Inbox inbox) {

    }

    @Override
    public void onCreation() {
        this.messagesIDs = new LinkedList<>();
        int time = 1;
        FloodingTimer ft = new FloodingTimer();
        ft.startRelative(time, this);
        ft.onReady();
    }
}
