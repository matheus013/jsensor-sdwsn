package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.SDWSN.CustomGlobal;
import projects.SDWSN.messages.Sense;
import projects.SDWSN.probability.Dice;
import projects.SDWSN.service.TypeSense;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.LinkedList;
import java.util.Random;

import static projects.SDWSN.statics.SenseEnumSingleton.typeSenses;


public class Sensor extends Node {
    private boolean isLive = true;
    private LinkedList<TypeSense> sensors;
    private LinkedList<Long> messagesIDs;
    private int parent;

    @Override
    public void handleMessages(Inbox inbox) {
        if (!isLive)
            return;
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
        this.sensors = new LinkedList<>();
        this.parent = -1;

        int n_sensors = new Random().nextInt(4) + 1;

        for (int i = 0; i < n_sensors; i++)
            if (Dice.get(1 / (sensors.size() + 1.0)))
                sensors.add(typeSenses[i]);
    }

    public Sense onSense() {
        return new Sense(new Random().nextDouble(), this.getID()).setDestination(parent);
    }

    public boolean isLive() {
        return isLive;
    }


    public synchronized int getParent() {
        return parent;
    }

    public synchronized void setParent(int parent) {
        this.parent = parent;

    }

    public LinkedList<TypeSense> getSensors() {
        return sensors;
    }


    public void test() {
        System.out.print("Sensor ID: " + getID());
        System.out.println(" Parent ID: " + parent);
    }

    public JsonObject toJsonObject() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        return jsonObjectBuilder
                .add("round", CustomGlobal.rounds)
                .add("ID", this.ID)
                .add("parent", this.parent)
                .add("isLife", isLive)
                .build();
    }
}
