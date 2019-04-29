package projects.SDWSN.probability;

import java.util.Random;

public class Dice {


    public static boolean get(double rate){
        return new Random().nextDouble() < rate;
    }
}
