package projects.SDWSN.messages;

import jsensor.nodes.Node;
import jsensor.nodes.messages.Message;
import projects.SDWSN.service.Ambient;

public class Request extends Message {
    private Ambient ambient;
    private Node user;
    private String command;

    public Request(Node user, Ambient ambient, String command) {
        super(user.getChunk());
        this.user = user;
        this.ambient = ambient;
        this.command = command;

    }

    Request(Node user, Ambient ambient, String command, long id) {
        this.user = user;
        this.ambient = ambient;
        this.command = command;
        setID(id);

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

    public String getCommand() {
        return command;
    }

    public Request setCommand(String command) {
        this.command = command;
        return this;
    }

    @Override
    public Message clone() {
        return new Request(user, ambient, command, getID());
    }
}
