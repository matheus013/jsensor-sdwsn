package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.messages.ServicesReportMessage;
import projects.SDWSN.service.ServiceSensor;
import projects.SDWSN.service.TypeSense;

import java.util.LinkedList;

import static projects.SDWSN.statics.EnumSingleton.environments;

public class SubController extends Node {


    private LinkedList<Integer> sensorsIDs;
    private LinkedList<ServiceSensor> services;
    private LinkedList<Long> messagesIDs;

    @Override
    public synchronized void handleMessages(Inbox inbox) {
        findSensors();
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (messagesIDs.contains(message.getID()))
                continue;
            messagesIDs.add(message.getID());

            if (message instanceof ServicesReportMessage) {
                ServicesReportMessage servicesReportMessage = (ServicesReportMessage) message;
                Sensor sensor = (Sensor) Jsensor.getNodeByID(servicesReportMessage.getSender());

                System.out.println(servicesReportMessage.getID());

                sensorsIDs.add(sensor.getID());
                System.out.println("Services from " + sensor.getID() + " reported");
                for (TypeSense i : sensor.getSensors()) {
                    services.add(new ServiceSensor(i, sensor.getID(), environments[this.getID()]));
                }
            }
        }
    }


    @Override
    public void onCreation() {
        sensorsIDs = new LinkedList<>();
        messagesIDs = new LinkedList<>();
        services = new LinkedList<>();

        findSensors();
    }

    public void test() {
        System.out.println("SubController ID: " + getID());
        if (sensorsIDs.isEmpty()) System.out.println("\tEmpty sensors");
        for (Integer i : sensorsIDs) {
            System.out.println("\tSensor ID: " + i);
        }
    }

    private synchronized void findSensors() {
        if (sensorsIDs.size() >= 10)
            return;
        if (!this.getNeighbours().getNodesList().isEmpty()) {
            for (Node node : this.getNeighbours().getNodesList()) {
                if (!sensorsIDs.contains(node.getID())
                        && node instanceof Sensor
                        && ((Sensor) node).getParent() == -1) {
                    sensorsIDs.add(node.getID());
                    ((Sensor) node).setParent(this.getID());
                }
            }
        }
    }


}
