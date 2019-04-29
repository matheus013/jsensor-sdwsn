package projects.SDWSN.Nodes;

import jsensor.nodes.messages.Inbox;

import java.util.LinkedList;


public class Controller extends Device {

    public int received = 0;
    private LinkedList<Long> soIDs;

    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasMoreMessages()) {

        }
    }
}
