package projects.SDWSN.connectivityModels;

import jsensor.nodes.Node;
import jsensor.nodes.models.ConnectivityModel;
import projects.SDWSN.nodes.Controller;
import projects.SDWSN.nodes.Sensor;
import projects.SDWSN.nodes.SubController;
import projects.SDWSN.nodes.User;


public class NodeConnectivity extends ConnectivityModel {
    @Override
    public synchronized boolean isConnected(Node from, Node to) {
//        return true;
        if (from instanceof Controller && to instanceof SubController ||
                to instanceof Controller && from instanceof SubController) return true;

        else if (from instanceof Controller && to instanceof User ||
                to instanceof Controller && from instanceof User) return true;

        else return from instanceof Sensor && to instanceof SubController ||
                    to instanceof Sensor && from instanceof SubController;
    }

    @Override
    public boolean isNear(Node from, Node to) {
        return true;
    }

}
