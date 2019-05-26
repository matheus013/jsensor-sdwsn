package projects.SDWSN.messages;

import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.service.TypeSense;


public class Sense extends Message {
    private double read;
    private int sender;
    private int destination;
    private int hops;
    private short chunk;
    private TypeSense type;

    public Sense(double read, int sender, int destination, int hops, short chunk, TypeSense type, long ID) {
        this.setHops(hops)
                .setRead(read)
                .setSender(sender)
                .setDestination(destination)
                .setChunk(chunk)
                .setType(type)
                .setID(ID);
    }


    public Sense(double read, int sender) {
        super(Jsensor.getNodeByID(sender).getChunk());
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

    public int getSender() {
        return sender;
    }

    public Sense setSender(int sender) {
        this.sender = sender;
        return this;
    }

    public int getDestination() {
        return destination;
    }

    public Sense setDestination(int destination) {
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
    public String toString() {
        return "SubController ID: " + destination + "from: " + sender + "\tread: " + read;
    }

    @Override
    public Message clone() {
        return new Sense(this.getRead(), this.getSender(), this.getDestination(), this.getHops(), this.getChunk(), this.getType(), this.getID());
    }
}
