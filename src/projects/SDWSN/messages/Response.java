package projects.SDWSN.messages;

import jsensor.nodes.messages.Message;

public class Response extends Message {
    private long requestID;
    private double value;
    private String type;
    @Override
    public Message clone() {
        return null;
    }
}
