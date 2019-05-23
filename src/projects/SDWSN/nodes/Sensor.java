package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.messages.Sense;
import projects.SDWSN.probability.Dice;
import projects.SDWSN.service.TypeSense;

import java.util.LinkedList;
import java.util.Random;

import static projects.SDWSN.statics.EnumSingleton.typeSenses;


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
        return new Sense(new Random().nextDouble(), this.parent);
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
}
