package projects.SDWSN.statics;

import jsensor.nodes.Node;

public class Constants {
    public static double dist(Node nodeByID, Node nodeByID1) {
        return Math.sqrt(Math.pow(nodeByID.getPosition().getPosX() - nodeByID1.getPosition().getPosX(), 2) + 
                Math.pow(nodeByID.getPosition().getPosY() - nodeByID1.getPosition().getPosY(), 2));
    }
}
