
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public class OpponentsBuilder implements OpponentsBuilderInterface {

    private transient OpponentInterface player1;
    private transient OpponentInterface player2;

    public final OpponentsBuilderInterface player1(
            final OpponentInterface player1) {
        this.player1 = player1;
        return this;
    }

    public final OpponentsBuilderInterface player2(
            final OpponentInterface player2) {
        this.player2 = player2;
        return this;
    }

    public OpponentsInterface build() {
        return new Opponents(this.player1, this.player2);
    }

}