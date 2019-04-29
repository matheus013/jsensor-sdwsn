package projects.FailDetect.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.runtime.Jsensor;
import projects.FailDetect.Timers.FloodingTimer;

import java.util.LinkedList;

public class DeviceNode extends Node {
    protected int[] routerTable;
    protected int[] jumpCost;
    protected LinkedList<Long> messagesIDs;

    @Override
    public void handleMessages(Inbox inbox) {

    }

    @Override
    public void onCreation() {
        this.messagesIDs = new LinkedList<Long>();
        this.routerTable = new int[Jsensor.numNodes + 1];
        this.jumpCost = new int[Jsensor.numNodes + 1];
        int time = 1;
        FloodingTimer ft = new FloodingTimer();
        ft.startRelative(time, this);
        ft.onReady();
    }
}
