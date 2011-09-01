
package abstractions.gameplay;

import abstractions.adversity.AdversityInterface;
import abstractions.game.GameInterface;

// TODO refactoring possible GamePlay = Context
public class GamePlay implements GamePlayInterface {

    private final AdversityInterface adversity;
    private final GameInterface game;

    public GamePlay(final GameInterface game, final AdversityInterface adversity) {
        this.game = game;
        this.adversity = adversity;
    }

    @Override
    public GameInterface getGame() {
        return this.game;
    }

    @Override
    public AdversityInterface getAdversity() {
        return this.adversity;
    }

}
