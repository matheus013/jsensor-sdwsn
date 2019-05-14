package projects.SDWSN.Nodes;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.Events.InitSensorParent;
import projects.SDWSN.Events.ServicesReport;
import projects.SDWSN.Messages.InitControl;
import projects.SDWSN.Messages.ServicesReportMessage;
import projects.SDWSN.Service.ActionActuator;
import projects.SDWSN.Service.Service;
import projects.SDWSN.Service.TypeSense;

import java.util.LinkedList;

public class SubController extends Node {
    private LinkedList<Integer> sensorsIDs;
    private LinkedList<Service> services;

    @Override
    public void handleMessages(Inbox inbox) {

        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();
            if (message instanceof ServicesReportMessage) {
                ServicesReportMessage servicesReportMessage = (ServicesReportMessage) message;
                Sensor sensor = (Sensor) Jsensor.getNodeByID(servicesReportMessage.getSender());
                sensorsIDs.add(sensor.getID());
                System.out.println(" ");
                for (TypeSense i : sensor.getSensors()) {
                    services.add(new Service(ActionActuator.TURN_ON, i, sensor.getID()));
                    services.add(new Service(ActionActuator.TURN_OFF, i, sensor.getID()));
                    services.add(new Service(ActionActuator.DECREASE, i, sensor.getID()));
                    services.add(new Service(ActionActuator.INCREASE, i, sensor.getID()));
                    services.add(new Service(ActionActuator.GET, i, sensor.getID()));
                }
            }
        }
    }


    @Override
    public void onCreation() {
        sensorsIDs = new LinkedList<>();
        int time = 1;
        InitSensorParent ft = new InitSensorParent();
        ft.startRelative(time, this);
        ft.fire();
    }

    public void test() {
        if (sensorsIDs.isEmpty()) return;
        System.out.println("SubController ID: " + getID());
        for (Integer i : sensorsIDs) {
            System.out.println("\tID: " + i);
        }
    }
}
