package projects.SDWSN.messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;
import jsensor.runtime.Jsensor;
import projects.SDWSN.service.Action;
import projects.SDWSN.service.Ambient;
import projects.SDWSN.service.Service;
import projects.SDWSN.service.TypeSense;

public class Request extends Message {
    private Ambient ambient;
    private Node user;
    private Action command;
    private TypeSense typeSense;
    private int slaveID;
    private Service service;

    public Request(Node user, Ambient ambient, Action command) {
        super(user.getChunk());
        this.user = user;
        this.ambient = ambient;
        this.command = command;

    }

    Request(Node user, Ambient ambient, Action command, long id) {
        this.user = user;
        this.ambient = ambient;
        this.command = command;
        setID(id);

    }

    public Request(Service service) {
        setService(service);
    }

    public Service getService() {
        return service;
    }

    public Request setService(Service service) {
        this.service = service;
        return this;
    }

    public Node getSlaveNode() {
        return Jsensor.getNodeByID(slaveID);
    }

    public int getSlaveID() {
        return slaveID;
    }

    public Request setSlaveID(int slaveID) {
        this.slaveID = slaveID;
        return this;
    }

    public TypeSense getTypeSense() {
        return typeSense;
    }

    public Request setTypeSense(TypeSense typeSense) {
        this.typeSense = typeSense;
        return this;
    }

    public Ambient getAmbient() {
        return ambient;
    }

    public Request setAmbient(Ambient ambient) {
        this.ambient = ambient;
        return this;
    }

    public Node getUser() {
        return user;
    }

    public Request setUser(Node user) {
        this.user = user;
        return this;
    }

    public Action getCommand() {
        return command;
    }

    public Request setCommand(Action command) {
        this.command = command;
        return this;
    }

    @Override
    public Message clone() {
        return new Request(user, ambient, command, getID()).setTypeSense(typeSense).setSlaveID(slaveID);
    }
}
