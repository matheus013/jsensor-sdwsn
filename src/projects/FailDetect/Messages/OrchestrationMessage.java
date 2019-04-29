package projects.FailDetect.Messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;

public class OrchestrationMessage extends Message {

    private String msg;
    private Node sender;
    private Node destination;

    public Node getSink() {
        return sink;
    }

    public OrchestrationMessage setSink(Node sink) {
        this.sink = sink;
        return this;
    }

    private Node sink;

    private int hops;
    short chunk;

    public OrchestrationMessage(Node sender, Node destination, Node sink, int hops, String message, byte chunk) {
        //Call to create a new ID
        super(chunk);
        this.sink = sink;
        this.msg = message;
        this.sender = sender;
        this.destination = destination;
        this.hops = hops;
        this.chunk = chunk;
    }

    private OrchestrationMessage(String msg, Node sender, Node destination, Node sink, int hops, long ID) {
        //Call to set the ID
        this.setID(ID);
        this.sink = sink;
        this.msg = msg;
        this.sender = sender;
        this.destination = destination;
        this.hops = hops;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public short getChunk() {
        return chunk;
    }

    public Node getSender() {
        return sender;
    }

    public void setSender(Node sender) {
        this.sender = sender;
    }

    @Override
    public Message clone() {
        return new OrchestrationMessage(msg, sender, destination, sink, hops + 1, this.getID());
    }
}