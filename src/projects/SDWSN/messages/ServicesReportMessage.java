package projects.SDWSN.messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;
import projects.SDWSN.nodes.Sensor;
import projects.SDWSN.service.TypeSense;

import java.util.LinkedList;

public class ServicesReportMessage extends Message {
    private LinkedList<TypeSense> typeSenses;
    private int destination;
    private int sender;

    public ServicesReportMessage(Node node) {
        super(node.getChunk());
        Sensor sensor = (Sensor) node;
        this.destination = sensor.getParent();
        this.typeSenses = sensor.getSensors();

    }

    public ServicesReportMessage(int sender, int destination, LinkedList<TypeSense> typeSenses, long id) {
        this.destination = destination;
        this.sender = sender;
        this.typeSenses = typeSenses;
        this.setID(id);
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
        return new ServicesReportMessage(this.getSender(), this.getDestination(),
                this.getTypeSenses(), this.getID());
    }
}
