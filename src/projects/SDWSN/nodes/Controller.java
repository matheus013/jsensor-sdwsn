package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.events.FloodingTimer;
import projects.SDWSN.messages.Sense;

import java.util.LinkedList;


public class Controller extends Node {

    private LinkedList<Integer> soIDs;
    private LinkedList<Long> messagesIDs;

    @Override
    public void handleMessages(Inbox inbox) {
        findController();
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (messagesIDs.contains(message.getID()))
                continue;
            messagesIDs.add(message.getID());

            if (message instanceof Sense) {

            }
        }
    }

    @Override
    public void onCreation() {
        soIDs = new LinkedList<>();
        messagesIDs = new LinkedList<>();
        findController();
        FloodingTimer ft = new FloodingTimer();
        ft.startRelative(1, this);


    }

    public void test() {
        System.out.println("Controller ID: " + getID());
        if (soIDs.isEmpty()) System.out.println("\tEmpty sub controllers");
        for (Integer i : soIDs) {
            System.out.println("\t" + Jsensor.getNodeByID(i).getClass().getSimpleName() + " ID: " + i);
        }
    }

    public synchronized void findController() {
        if (!this.getNeighbours().getNodesList().isEmpty()) {
            for (Node node : this.getNeighbours().getNodesList()) {
                if (!soIDs.contains(node.getID())) {
                    soIDs.add(node.getID());
                    if (node instanceof User) {
                        ((User) node).setParent(this);
                    } else if (node instanceof SubController) {
                        ((SubController) node).setParent(this);
                    }
                }
            }
        }
    }
}
