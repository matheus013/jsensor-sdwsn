package projects.FailDetect.fault;

import jsensor.runtime.Jsensor;
import projects.FailDetect.Counts;
import projects.FailDetect.Nodes.SensorNode;
import projects.FailDetect.Nodes.ControllerNode;

import java.util.Random;

public class ChangeStatus {
    private ChangeStatus() {

    }

    public static void next(int nodeID) {
        double p = 0.00001333;
        double r = 0.601795;

        if (Jsensor.getNodeByID(nodeID) instanceof ControllerNode)
            return;

        boolean live = ((SensorNode) Jsensor.getNodeByID(nodeID)).isLive();

        if (live) {
            if (new Random().nextDouble() <= p) {
                ((SensorNode) Jsensor.getNodeByID(nodeID)).setLive(false);
                Counts.countFaults();
            }
        } else {
            if (new Random().nextDouble() <= r) {
                ((SensorNode) Jsensor.getNodeByID(nodeID)).setLive(true);
                Counts.countRestore();
            }
        }

    }
}
