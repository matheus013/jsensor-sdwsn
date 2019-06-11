package projects.SDWSN.log;

import jsensor.nodes.Node;
import projects.SDWSN.nodes.Controller;
import projects.SDWSN.nodes.Sensor;
import projects.SDWSN.nodes.SubController;
import projects.SDWSN.nodes.User;

public class LogJson {

    public static void log(Node node) {
        if (node == null) return;
        if (node instanceof Controller) {
            Log.add(((Controller) node).toJsonObject(),
                    Controller.class.getSimpleName() + ".json");

        } else if (node instanceof SubController) {

        } else if (node instanceof Sensor) {

        } else if (node instanceof User) {

        }
    }
}
