package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;

import java.util.LinkedList;

public class User extends Node {
    private LinkedList<Long> messagesIDs;
    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (messagesIDs.contains(message.getID()))
                continue;
            messagesIDs.add(message.getID());
        }
    }


    @Override
    public void onCreation() {
        this.messagesIDs = new LinkedList<>();
    }

}
