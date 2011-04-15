
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.StrategyInterface;

public class Opponent implements OpponentInterface {

    private final transient PlayerInterface player;
    private final transient StrategyInterface strategy;
    private transient GameInterface context;

    public Opponent(final PlayerInterface player,
            final StrategyInterface strategy) {
        this.player = player;
        this.strategy = strategy;
    }

    public PlayerInterface getPlayer() {
        return this.player;
    }

    public StrategyInterface getStrategy() {
        return this.strategy;
    }

    public LegalMoveInterface selectMove(
            final List<LegalMoveInterface> legalMoves) {
        return this.strategy.computeStrategicMoveFrom(legalMoves);
    }

    public void setContext(final GameInterface context) {
        this.context = context;
        this.strategy.injectContext(context);
    }

    public GameInterface getContext() {
        return this.context;
    }

    @Override
    public String toString() {
        return this.getPlayer().getName();
    }

}