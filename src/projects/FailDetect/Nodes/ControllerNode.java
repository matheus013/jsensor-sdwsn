package projects.FailDetect.Nodes;

import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import projects.FailDetect.Messages.ChoreographyMessage;
import projects.FailDetect.Messages.OrchestrationMessage;
import projects.FailDetect.Messages.RouterMessage;


public class ControllerNode extends DeviceNode {

    public int received = 0;

    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (message instanceof ChoreographyMessage) {
                ChoreographyMessage choreographyMessage = (ChoreographyMessage) message;

                if (this.messagesIDs.contains(choreographyMessage.getID())) {
                    continue;
                }

                this.messagesIDs.add(choreographyMessage.getID());

                if (choreographyMessage.getDestination().getID() == this.getID()) {
                    received++;
                } else {
                    this.multicast(choreographyMessage);
                }
            } else if (message instanceof OrchestrationMessage) {
                OrchestrationMessage orchestrationMessage = (OrchestrationMessage) message;
                orchestrationMessage.setDestination(orchestrationMessage.getSink());

                this.multicast(orchestrationMessage);
            } else if (message instanceof RouterMessage) {
                RouterMessage routerMessage = (RouterMessage) message;
//                if(routerMessage.)
            }
        }
    }



    public void updateRouterTable() {

    }
}
