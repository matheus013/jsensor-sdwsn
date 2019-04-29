package projects.SDWSN.Messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;
import projects.SDWSN.Nodes.TypeSense;


public class Sense extends Message {
    private double read;
    private Node sender;
    private Node destination;
    private int hops;
    private short chunk;
    private TypeSense type;

    public Sense() {
    }

    public Sense(double read, Node sender, Node destination, int hops, long ID) {
        this.setHops(hops)
                .setRead(read)
                .setSender(sender)
                .setDestination(destination)
                .setID(ID);
    }

    public Sense(double read, Node sender, Node destination, byte chunk) {
        this.setHops(hops)
                .setRead(read)
                .setSender(sender)
                .setDestination(destination)
                .setChunk(chunk);
    }

    public Sense(double read, Node sender, Node destination) {
        this.setHops(hops)
                .setRead(read)
                .setSender(sender)
                .setDestination(destination);
    }

    public Sense(double read, Node sender) {
        this.setHops(hops)
                .setRead(read)
                .setSender(sender);
    }

    public double getRead() {
        return read;
    }

    public Sense setRead(double read) {
        this.read = read;
        return this;
    }

    public Node getSender() {
        return sender;
    }

    public Sense setSender(Node sender) {
        this.sender = sender;
        return this;
    }

    public Node getDestination() {
        return destination;
    }

    public Sense setDestination(Node destination) {
        this.destination = destination;
        return this;
    }

    public int getHops() {
        return hops;
    }

    public Sense setHops(int hops) {
        this.hops = hops;
        return this;
    }

    public short getChunk() {
        return chunk;
    }

    public Sense setChunk(short chunk) {
        this.chunk = chunk;
        return this;
    }

    public TypeSense getType() {
        return type;
    }

    public Sense setType(TypeSense type) {
        this.type = type;
        return this;
    }

    @Override
    public Message clone() {
        return null;
    }
}
