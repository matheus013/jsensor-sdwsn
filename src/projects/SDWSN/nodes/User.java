package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.SDWSN.CustomGlobal;
import projects.SDWSN.messages.Request;
import projects.SDWSN.statics.SenseEnumSingleton;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.LinkedList;
import java.util.Random;

public class User extends Node {
    private int requestServices = 0;
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
        requestServices++;
        return new Request(this,
                SenseEnumSingleton.environments[new Random().nextInt(5)],
                SenseEnumSingleton.actions[new Random().nextInt(5)],
                SenseEnumSingleton.typeSenses[new Random().nextInt(5)]);
    }

    public JsonObject toJsonObject() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        return jsonObjectBuilder
                .add("round", CustomGlobal.rounds)
                .add("ID", this.ID)
                .add("requestServices", requestServices)
                .build();
    }
}
