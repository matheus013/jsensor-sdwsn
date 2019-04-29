package projects.SDWSN.DistributionModels;

import jsensor.nodes.Node;
import jsensor.nodes.models.DistributionModelNode;
import jsensor.utils.Configuration;
import jsensor.utils.Position;

public class RandomDistribution extends DistributionModelNode {
    @Override
    public Position getPosition(Node n) {
        return new Position(n.getRandom().nextInt(Configuration.dimX), n.getRandom().nextInt(Configuration.dimY));
    }
}
