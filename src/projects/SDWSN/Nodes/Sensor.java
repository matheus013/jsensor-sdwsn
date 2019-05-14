package projects.SDWSN.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.SDWSN.Events.ServicesReport;
import projects.SDWSN.Messages.InitControl;
import projects.SDWSN.Messages.Sense;
import projects.SDWSN.Service.TypeSense;
import projects.SDWSN.probability.Dice;

import java.util.LinkedList;
import java.util.Random;


public class Sensor extends Node {
    private boolean isLive = true;
    private LinkedList<TypeSense> sensors;
    private LinkedList<TypeSense> messagesIDs;
    private int parent;
    private int channel;

    @Override
    public void handleMessages(Inbox inbox) {
        if (!isLive)
            return;

        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (message instanceof InitControl) {
                InitControl initControl = (InitControl) message;
                this.parent = initControl.getNode().getID();
                this.channel = parent;

                int time = 1;

                ServicesReport ft = new ServicesReport();
                ft.startRelative(time, this);
                ft.fire();
            }

        }
    }


    @Override
    public void onCreation() {
        this.messagesIDs = new LinkedList<>();
        this.sensors = new LinkedList<>();
        this.channel = -1;
        this.parent = -1;

        int n_sensors = new Random().nextInt(4);

        TypeSense[] typeSenses = {TypeSense.TEMPERATURE, TypeSense.PRESSURE,
                TypeSense.LUMINOSITY, TypeSense.HUMIDITY};

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

    public Sensor setLive(boolean live) {
        this.isLive = live;
        return this;
    }

    public int getParent() {
        return parent;
    }

    public Sensor setParent(int parent) {
        this.parent = parent;
        return this;
    }

    public LinkedList<TypeSense> getSensors() {
        return sensors;
    }

    public int getChannel() {
        return channel;
    }

    public void test() {
        if (parent == -1) return;
        System.out.print("Sensor ID: " + getID());
        System.out.println(" Parent ID: " + parent);
    }
}
