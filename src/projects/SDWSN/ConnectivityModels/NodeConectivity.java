package projects.SDWSN.ConnectivityModels;

import jsensor.nodes.Node;
import jsensor.nodes.models.ConnectivityModel;
import projects.SDWSN.Nodes.Controller;
import projects.SDWSN.Nodes.Sensor;
import projects.SDWSN.Nodes.SubController;
import projects.SDWSN.Nodes.User;

public class NodeConectivity extends ConnectivityModel {
    @Override
    public boolean isConnected(Node from, Node to) {

        if (from instanceof Controller && to instanceof SubController ||
                to instanceof Controller && from instanceof SubController) return true;

        else if (from instanceof Controller && to instanceof User ||
                to instanceof Controller && from instanceof User) return true;


        else if (from instanceof Sensor && to instanceof SubController) {
            if (((Sensor) from).getChannel() == -1) return true;
            return ((Sensor) from).getChannel() == to.getID();
        } else if (to instanceof Sensor && from instanceof SubController) {
            if (((Sensor) to).getChannel() == -1) return true;
            return ((Sensor) to).getChannel() == from.getID();
        }

        return false;
    }


    @Override
    public boolean isNear(Node from, Node to) {
        return true;
    }

}
