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
                    "json/" + Controller.class.getSimpleName() + ".json");

        } else if (node instanceof SubController) {
            Log.add(((SubController) node).toJsonObject(),
                    "json/" + SubController.class.getSimpleName() + ".json");
        } else if (node instanceof Sensor) {
            Log.add(((Sensor) node).toJsonObject(),
                    "json/" + Sensor.class.getSimpleName() + ".json");
        } else if (node instanceof User) {
            Log.add(((User) node).toJsonObject(),
                    "json/" + User.class.getSimpleName() + ".json");
        }
    }
}
