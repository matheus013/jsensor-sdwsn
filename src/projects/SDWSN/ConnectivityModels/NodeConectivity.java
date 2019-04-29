package projects.SDWSN.ConnectivityModels;

import jsensor.nodes.Node;
import jsensor.nodes.models.ConnectivityModel;

public class NodeConectivity extends ConnectivityModel {
    @Override
    public boolean isConnected(Node from, Node to) {
        return RouterTree.adj[from.getID()][to.getID()] == 1;
    }


    @Override
    public boolean isNear(Node from, Node to) {
        return RouterTree.adj[from.getID()][to.getID()] == 1;
    }

}
