package projects.SDWSN.Messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;

public class InitControl extends Message {
    private Node node;
    private int hops;

    public InitControl(Node node, int hops) {
        this.setHops(hops)
                .setNode(node);
    }

    public Node getNode() {
        return node;
    }

    public InitControl setNode(Node node) {
        this.node = node;
        return this;
    }

    public int getHops() {
        return hops;
    }

    public InitControl setHops(int hops) {
        this.hops = hops;
        return this;
    }

    @Override
    public Message clone() {
        return null;
    }
}
