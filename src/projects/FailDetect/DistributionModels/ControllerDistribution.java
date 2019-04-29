package projects.FailDetect.DistributionModels;

import jsensor.nodes.Node;
import jsensor.nodes.models.DistributionModelNode;
import jsensor.runtime.Jsensor;
import jsensor.utils.Position;

public class ControllerDistribution extends DistributionModelNode {
    @Override
    public Position getPosition(Node n) {
        int sizeX = Jsensor.getDimX();
        int sizeY = Jsensor.getDimY();
        System.out.println(sizeX + "x" + sizeY);
        return new Position(sizeX / 2, sizeY / 2);
    }
}
