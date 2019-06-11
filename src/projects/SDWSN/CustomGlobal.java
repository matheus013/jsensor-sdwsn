package projects.SDWSN;

import jsensor.nodes.Node;
import jsensor.runtime.AbsCustomGlobal;
import jsensor.runtime.Jsensor;
import projects.SDWSN.events.UserRequestService;
import projects.SDWSN.events.VirtualSense;
import projects.SDWSN.log.LogJson;
import projects.SDWSN.nodes.Controller;
import projects.SDWSN.nodes.Sensor;
import projects.SDWSN.nodes.SubController;
import projects.SDWSN.nodes.User;
import projects.SDWSN.probability.Dice;


public class CustomGlobal extends AbsCustomGlobal {
    private int rounds = 1;


    @Override
    public boolean hasTerminated() {
        int max_rounds = 500;
        return ++rounds > max_rounds;
    }

    @Override
    public void preRun() {
    }

    @Override
    public void preRound() {
        for (int i = 1; i <= Jsensor.getNumNodes(); i++) {
            Node node = Jsensor.getNodeByID(i);
            if (node instanceof Sensor) {
                double prob_event = 0.10;
                if (Dice.get(prob_event) && ((Sensor) node).isLive()) {
                    int time = rounds + 1;
                    VirtualSense virtualSense = new VirtualSense();
                    virtualSense.startRelative(time, node);
                } else continue;
            }
            if (node instanceof User) {
                double prob_event = 0.5;
                if (Dice.get(prob_event)) {
                    int time = rounds + 1;
                    UserRequestService requestService = new UserRequestService();
                    requestService.startRelative(time, node);
                } else continue;
            }
            if (node instanceof Controller) {
                ((Controller) node).run();
            }

        }
    }

    @Override
    public void postRound() {
        for (int i = 1; i <= Jsensor.getNumNodes(); i++) {
            Node node = Jsensor.getNodeByID(i);
            LogJson.log(node);
            if (node instanceof SubController) {
                ((SubController) node).updateMessages();
            } else if (node instanceof Controller) {
                ((Controller) node).updateMessages();
            }
        }
    }

    @Override
    public void postRun() {
        boolean debug = true;
        if (debug) {
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
    }
}
