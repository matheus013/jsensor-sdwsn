package projects.FailDetect.Timers;

import jsensor.nodes.Node;
import jsensor.nodes.events.TimerEvent;
import jsensor.runtime.Jsensor;
import projects.FailDetect.Counts;
import projects.FailDetect.Messages.ChoreographyMessage;
import projects.FailDetect.Messages.RouterMessage;


public class FloodingTimer extends TimerEvent {

    @Override
    public void fire() {
        Node destination = this.node.getRandomNode("Sensor");

        ChoreographyMessage message = new ChoreographyMessage(this.node, destination, 0, "" + this.node.getID(), this.node.getChunk());

        String messageText = "" + this.node.getID() + " - ";

        message.setMsg(messageText);
        Jsensor.log("time: " + Jsensor.currentTime + "\t sensorID: " + this.node.getID() + "\t sendTo: " + destination.getID());

        Counts.countSent();
        this.node.multicast(message);
    }

    public void onReady() {
        RouterMessage message = new RouterMessage(this.node, 0, this.node.getChunk());


        Jsensor.log("time: " + Jsensor.currentTime + "\t sensorID: " + this.node.getID());

        this.node.multicast(message);
    }

}
