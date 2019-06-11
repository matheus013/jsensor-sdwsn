package projects.SDWSN.nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.messages.Request;
import projects.SDWSN.messages.Sense;
import projects.SDWSN.messages.ServicesReportMessage;
import projects.SDWSN.service.Service;
import projects.SDWSN.service.ServiceSensor;
import projects.SDWSN.service.TypeSense;

import java.util.HashMap;
import java.util.LinkedList;

import static projects.SDWSN.statics.EnumSingleton.environments;

public class SubController extends Node {
    private int lastCountMessages = 0;

    private LinkedList<Integer> sensorsIDs;
    private LinkedList<ServiceSensor> services;
    private LinkedList<Long> messagesIDs;
    private HashMap<Integer, Double> lastRead;
    private Node parent;

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
            if (message instanceof Sense) {
                Sense sense = (Sense) message;
                lastRead.put(sense.getSender(), sense.getRead());
            }

            if (message instanceof Request) {
                Request request = (Request) message;
                Service service = request.getService();

                switch (((ServiceSensor) service).getAction()) {
                    case UP:
                        service.increase();
                        break;
                    case DOWN:
                        service.decrease();
                        break;
                    case OFF:
                        service.turnOff();
                        break;
                    case ON:
                        service.turnOn();
                        break;
                    case GET:
                        service.getValue();
                        break;
                }
            }
        }
    }


    @Override
    public void onCreation() {
        sensorsIDs = new LinkedList<>();
        messagesIDs = new LinkedList<>();
        services = new LinkedList<>();
        lastRead = new HashMap<>();

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
        int ref = this.getID() % 10 - 1;
        if (!this.getNeighbours().getNodesList().isEmpty()) {
            for (Node node : this.getNeighbours().getNodesList()) {
                if (node.getID() > ref * 10 && node.getID() <= ref * 10 + 10
                        && node instanceof Sensor
                        && !sensorsIDs.contains(node.getID())
                        && ((Sensor) node).isLive()) {
                    sensorsIDs.add(node.getID());
                    ((Sensor) node).setParent(this.getID());
                }
            }
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void updateMessages() {
        lastCountMessages = messagesIDs.size();
    }
}
