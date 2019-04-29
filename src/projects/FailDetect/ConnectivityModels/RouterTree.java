package projects.FailDetect.ConnectivityModels;

import jsensor.runtime.Jsensor;

public class RouterTree {
    public static int[][] adj;
    public static int[][] cost;

    public static void init() {
        adj = new int[Jsensor.numNodes + 1][Jsensor.numNodes + 1];
        cost = new int[Jsensor.numNodes + 1][Jsensor.numNodes + 1];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                adj[i][j] = 1;
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}
