package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.SDWSN.messages.Request;
import projects.SDWSN.statics.EnumSingleton;

import java.util.LinkedList;
import java.util.Random;

public class User extends Node {
    private LinkedList<Long> messagesIDs;
    private Node parent;

    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (messagesIDs.contains(message.getID()))
                continue;
            messagesIDs.add(message.getID());
        }
    }

    public Node getParent() {
        return parent;
    }

    public User setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public void onCreation() {
        this.messagesIDs = new LinkedList<>();
    }


    public Request onRequest() {
        return new Request(this,
                EnumSingleton.environments[new Random().nextInt(5)],
                EnumSingleton.actions[new Random().nextInt(5)],
                EnumSingleton.typeSenses[new Random().nextInt(5)]);
    }
}
