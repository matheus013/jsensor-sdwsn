package projects.FailDetect.Messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;

public class RouterMessage extends Message {

    private Node sender;
    private int hops;
    short chunk;
    private int[] jumpCost;
    private int[] routerTable;

    public RouterMessage(Node sender, int hops, byte chunk) {
        //Call to create a new ID
        super(chunk);
        this.sender = sender;
        this.hops = hops;
        this.chunk = chunk;

        System.out.println("ROUTER MSG FROM: " + sender.getID());
    }

    private RouterMessage(Node sender, int hops, long ID) {
        //Call to set the ID
        this.setID(ID);
        this.sender = sender;
        this.hops = hops;
    }

    public RouterMessage setChunk(short chunk) {
        this.chunk = chunk;
        return this;
    }

    public int[] getJumpCost() {
        return jumpCost;
    }

    public RouterMessage setJumpCost(int[] jumpCost) {
        this.jumpCost = jumpCost;
        return this;
    }

    public int[] getRouterTable() {
        return routerTable;
    }

    public RouterMessage setRouterTable(int[] routerTable) {
        this.routerTable = routerTable;
        return this;
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
        return new RouterMessage(sender, hops + 1, this.getID());
    }
}


