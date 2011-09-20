
package fr.designpattern.zerosumgames.concretisations.reversi;

import fr.designpattern.zerosumgames.abstractions.immutable.ContextManagerInterface;

public final class mySignal implements SignalInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final ContextManagerInterface contextManager;

    /*-------------------------------------8<-------------------------------------*/

    public mySignal(final ContextManagerInterface contextManager) {
        this.contextManager = contextManager;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public void process() {
        this.contextManager.start();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.contextManager.toString();
    }

    /*-------------------------------------8<-------------------------------------*/

}