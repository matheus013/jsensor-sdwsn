package projects.SDWSN.nodes;

import javafx.util.Pair;
import jdk.nashorn.internal.objects.Global;
import jsensor.nodes.Node;
import jsensor.nodes.messages.Inbox;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import jsensor.utils.Configuration;
import projects.SDWSN.CustomGlobal;
import projects.SDWSN.events.FloodingTimer;
import projects.SDWSN.events.OrcRequestService;
import projects.SDWSN.messages.Request;
import projects.SDWSN.service.Ambient;
import projects.SDWSN.service.Service;
import projects.SDWSN.service.ServiceSensor;
import projects.SDWSN.service.TypeSense;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Controller extends Node {
    private int taskConflict = 0;
    private int lastCountMessages = 0;
    private int invalidRequest = 0;
    private LinkedList<Integer> soIDs;
    private LinkedList<Long> messagesIDs;
    private Queue<Service> taskQueue;
    private HashMap<Pair<Integer, TypeSense>, Boolean> lock;
    private HashMap<Pair<Integer, TypeSense>, Integer> timeLock;
    private HashMap<Pair<Integer, TypeSense>, Integer> durationLock;
    private int currentTime;

    @Override
    public void handleMessages(Inbox inbox) {
        findController();
        while (inbox.hasMoreMessages()) {
            Message message = inbox.getNextMessage();

            if (messagesIDs.contains(message.getID()))
                continue;
            messagesIDs.add(message.getID());

            if (message instanceof Request) {
                Request request = (Request) message;
                taskQueue.add(new ServiceSensor(request.getAmbient(), request.getTypeSense(), request.getCommand()));
            }
        }
    }

    @Override
    public void onCreation() {
        soIDs = new LinkedList<>();
        messagesIDs = new LinkedList<>();
        taskQueue = new LinkedList<>();
        lock = new HashMap<>();
        timeLock = new HashMap<>();
        durationLock = new HashMap<>();
        currentTime = 0;
        findController();
        FloodingTimer ft = new FloodingTimer();
        ft.startRelative(1, this);


    }

    public void test() {
        System.out.println("Controller ID: " + getID());
        if (soIDs.isEmpty()) System.out.println("\tEmpty sub controllers");
        for (Integer i : soIDs) {
            System.out.println("\t" + Jsensor.getNodeByID(i).getClass().getSimpleName() + " ID: " + i);
        }
    }

    private synchronized void findController() {
        if (!this.getNeighbours().getNodesList().isEmpty()) {
            for (Node node : this.getNeighbours().getNodesList()) {
                if (!soIDs.contains(node.getID())) {
                    if (node instanceof User) {
                        ((User) node).setParent(this);
                        soIDs.add(node.getID());
                    } else if (node instanceof SubController) {
                        ((SubController) node).setParent(this);
                        soIDs.add(node.getID());
                    }
                }
            }
        }
    }

    private int getResponsible(Ambient ambient) {
        for (Integer id : soIDs) {
            if (id % 10 == ambient.getValue())
                return id;
        }
        return -1;
    }

    private boolean isLocking(Pair<Integer, TypeSense> id) {
        return lock.get(id);
    }

    private void lock(Pair<Integer, TypeSense> id) {
        if (!lock.containsKey(id))
            lock.put(id, true);
        else lock.replace(id, true);
    }

    private void unlock(Pair<Integer, TypeSense> id) {
        if (lock.containsKey(id))
            lock.replace(id, false);
    }

    public void run() {
        currentTime++;
        for (int i = 0; i < 4; i++) {
            if (taskQueue.isEmpty())
                break;

            ServiceSensor service = (ServiceSensor) taskQueue.poll();
            assert service != null;
            int responsible = getResponsible(service.getAmbient());
            if (responsible == -1) {
                invalidRequest++;
                continue;
            }
            Pair<Integer, TypeSense> pairID = new Pair<>(responsible, service.getTypeSensor());

            if (pairID.getValue() == TypeSense.INVALID) {
                invalidRequest++;
                continue;
            }

            int time;
            if (!timeLock.containsKey(pairID)) {
                timeLock.put(pairID, 0);
                durationLock.put(pairID, service.getDuration());
                lock.put(pairID, false);
            }
            time = timeLock.get(pairID);

            if (currentTime - time >= durationLock.get(pairID))
                unlock(pairID);
            if (!isLocking(pairID)) {
                OrcRequestService orcRequestService = new OrcRequestService();
                orcRequestService.setRequest(new Request(service).setSlaveID(responsible));
                lock(new Pair<>(responsible, service.getTypeSensor()));
                timeLock.put(pairID, currentTime);
                durationLock.put(pairID, service.getDuration());
            } else {
                taskQueue.add(service);
                taskConflict++;
            }

        }
    }

    public void updateMessages() {
        lastCountMessages = messagesIDs.size();
    }

    public JsonObject toJsonObject() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        return jsonObjectBuilder
                .add("round", CustomGlobal.rounds)
                .add("ID", this.ID)
                .add("tasksInQueue", taskQueue.size())
                .add("taskConflict", taskConflict)
                .add("receivedMessages", lastCountMessages)
                .add("invalidRequest", invalidRequest)
                .build();
    }

}
