package projects.SDWSN.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;

import java.util.LinkedList;


public class Controller extends Node {

    public int received = 0;
    private LinkedList<Long> soIDs;

    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasMoreMessages()) {

        }
    }

    @Override
    public void onCreation() {
        soIDs = new LinkedList<>();

    }

    public void test() {
        if (soIDs.isEmpty()) return;
        System.out.println("Controller ID: " + getID());
        for (Long i : soIDs) {
            System.out.println("\tID: " + i);
        }
    }
}
