package projects.SDWSN;

import jsensor.nodes.Node;
import jsensor.runtime.AbsCustomGlobal;
import jsensor.runtime.Jsensor;
import projects.SDWSN.Events.VirtualSense;
import projects.SDWSN.Nodes.Controller;
import projects.SDWSN.Nodes.Sensor;
import projects.SDWSN.Nodes.SubController;
import projects.SDWSN.probability.Dice;


public class CustomGlobal extends AbsCustomGlobal {
    private double prob_event = 0.1;
    private int rounds = 1;


    @Override
    public boolean hasTerminated() {
        return false;
    }

    @Override
    public void preRun() {

    }

    @Override
    public void preRound() {
//        for (int i = 1; i <= Jsensor.getNumNodes(); i++)
//            ChangeStatus.next(i);

        for (int i = 1; i <= Jsensor.getNumNodes(); i++) {
            Node node = Jsensor.getNodeByID(i);
            if (!(node instanceof Sensor))
                continue;
            if (Dice.get(prob_event) && ((Sensor) node).isLive()) {
                VirtualSense virtualSense = new VirtualSense();
                virtualSense.setNode(node);
                virtualSense.fire();
            }
        }

        for (int i = 1; i <= Jsensor.getNumNodes(); i++) {
            Node node = Jsensor.getNodeByID(i);
            if (node instanceof Sensor) {
                ((Sensor) node).test();
            } else if (node instanceof SubController) {
                ((SubController) node).test();
            } else if (node instanceof Controller) {
                ((Controller) node).test();
            }
        }


    }

    @Override
    public void postRound() {
        if (++rounds > 30)
            Jsensor.runtime.setAbort(true);
    }

    @Override
    public void postRun() {

    }
}
