package projects.FailDetect.Nodes;

import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.FailDetect.ConnectivityModels.RouterTree;
import projects.FailDetect.Counts;
import projects.FailDetect.Messages.ChoreographyMessage;
import projects.FailDetect.Messages.OrchestrationMessage;
import projects.FailDetect.Messages.RouterMessage;


public class SensorNode extends DeviceNode {

    //    public int received = 0;
    private boolean live = true;
//    private LinkedList<Long> neighbors;

    @Override
    public synchronized void handleMessages(Inbox inbox) {
        if (!live)
            return;

        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (message instanceof ChoreographyMessage) {
                ChoreographyMessage choreographyMessage = (ChoreographyMessage) message;

                if (this.messagesIDs.contains(choreographyMessage.getID())) {
                    continue;
                }

                this.messagesIDs.add(choreographyMessage.getID());

                if (choreographyMessage.getDestination().getID() == this.getID()) {
                    Counts.countReceived();
                } else {
                    this.multicast(choreographyMessage);
                }
            } else if (message instanceof OrchestrationMessage) {
                this.multicast(message);
            } else if (message instanceof RouterMessage) {
                RouterMessage routerMessage = (RouterMessage) message;
                if (routerMessage.getHops() < 1) RouterTree.adj[routerMessage.getSender().getID()][this.getID()] = 1;
            }
        }
    }


    public boolean isLive() {
        return live;
    }

    public SensorNode setLive(boolean live) {
        this.live = live;
        return this;
    }


}
