package projects.SDWSN.Messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;
import projects.SDWSN.Nodes.Sensor;
import projects.SDWSN.Service.TypeSense;

import java.util.LinkedList;

public class ServicesReportMessage extends Message {
    private LinkedList<TypeSense> typeSenses;
    private int destination;
    private int sender;

    public ServicesReportMessage(Node node) {
        if (node instanceof Sensor) {
            Sensor sensor = (Sensor) node;
            this.destination = sensor.getParent();
            this.typeSenses = sensor.getSensors();
        }
    }

    public LinkedList<TypeSense> getTypeSenses() {
        return typeSenses;
    }

    public int getDestination() {
        return destination;
    }

    public int getSender() {
        return sender;
    }

    @Override
    public Message clone() {
        return null;
    }
}
