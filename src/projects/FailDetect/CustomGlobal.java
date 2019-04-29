package projects.FailDetect;

import jsensor.nodes.Node;
import jsensor.runtime.AbsCustomGlobal;
import jsensor.runtime.Jsensor;
import projects.FailDetect.ConnectivityModels.RouterTree;
import projects.FailDetect.Nodes.ControllerNode;
import projects.FailDetect.Nodes.SensorNode;
import projects.FailDetect.Timers.FloodingTimer;
import projects.FailDetect.fault.ChangeStatus;

import java.util.Random;

/*
    os sensores estão ativos por `n_rounds` rounds, mas
    simulação continua por mais alguns rounds 5% do valor
 */

public class CustomGlobal extends AbsCustomGlobal {
    private double prob_event = 0.1;


    @Override
    public boolean hasTerminated() {
        return false;
    }

    @Override
    public void preRun() {
        RouterTree.init();

    }

    @Override
    public void preRound() {

        for (int i = 1; i <= Jsensor.getNumNodes(); i++)
            ChangeStatus.next(i);

        for (int i = 1; i <= Jsensor.getNumNodes(); i++) {
            Node node = Jsensor.getNodeByID(i);
            if (node instanceof ControllerNode)
                continue;
            if (new Random().nextDouble() <= prob_event &&
                    ((SensorNode) node).isLive()) {
                FloodingTimer floodingTimer = new FloodingTimer();
                floodingTimer.setNode(node);
                floodingTimer.fire();
            }
        }
    }

    @Override
    public void postRound() {
        Counts.countRound();
        int n_rounds = 200;
        if (Counts.rounds >= n_rounds * 1.05)
            Jsensor.runtime.setAbort(true);
        if (Counts.rounds % 100 == 0) {
            System.out.println("round: " + Counts.rounds);
        }
        // TODO Register faults
    }

    @Override
    public void postRun() {

        System.out.println("rounds: " + Counts.rounds);
        System.out.println("received: " + Counts.received);
        System.out.println("sent: " + Counts.sent);
        System.out.println("faults node: " + Counts.faults_node);
        System.out.println("restore node: " + Counts.restore_node);
        System.out.println("success rate: " + Counts.received / (double) Counts.sent);
    }
}
