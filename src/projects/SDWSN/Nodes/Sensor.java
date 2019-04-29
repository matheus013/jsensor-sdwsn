package projects.SDWSN.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.SDWSN.Events.ServicesReport;
import projects.SDWSN.Messages.InitControl;
import projects.SDWSN.Messages.Sense;
import projects.SDWSN.probability.Dice;

import java.util.LinkedList;
import java.util.Random;


public class Sensor extends Device {
    private boolean isLive = true;
    private LinkedList<TypeSense> sensors;
    private Node parent;

    @Override
    public void handleMessages(Inbox inbox) {
        if (!isLive)
            return;

        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (message instanceof InitControl) {
                InitControl initControl = (InitControl) message;
                this.parent = initControl.getNode();

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
        int n_sensors = new Random().nextInt(4);

        TypeSense[] typeSenses = {TypeSense.TEMPERATURE, TypeSense.PRESSURE,
                TypeSense.LUMINOSITY, TypeSense.HUMIDITY};

        for (int i = 0; i < n_sensors; i++)
            if (Dice.get(1 / (sensors.size() + 1.0)))
                sensors.add(typeSenses[i]);
    }

    public Sense onSense() {
        return new Sense(new Random().nextDouble(), this.parent)
                .setType(sensors.get(new Random().nextInt(sensors.size())));
    }

    public boolean isLive() {
        return isLive;
    }

    public Sensor setLive(boolean live) {
        this.isLive = live;
        return this;
    }

    public Node getParent() {
        return parent;
    }

    public Sensor setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public LinkedList<TypeSense> getSensors() {
        return sensors;
    }
}
