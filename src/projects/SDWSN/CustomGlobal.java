package projects.SDWSN;

import jsensor.runtime.AbsCustomGlobal;

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

    }

    @Override
    public void preRound() {

    }

    @Override
    public void postRound() {

    }

    @Override
    public void postRun() {

    }
}
