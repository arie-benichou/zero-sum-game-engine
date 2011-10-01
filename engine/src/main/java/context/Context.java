
package context;

import java.util.List;

import scala.collection.immutable.Stack;
import context.entity.adversity.AdversityInterface;
import context.entity.game.GameInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.event.MoveInterface;

public final class Context implements ContextInterface {

    public static int instances;

    /*-------------------------------------8<-------------------------------------*/

    private final GameInterface game;
    private final AdversityInterface adversity;
    private final SideInterface sideToPlay;
    private final Stack<MoveInterface> history;

    /*-------------------------------------8<-------------------------------------*/

    public static ContextInterface from(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
        return new Context(sideToPlay, adversity, game);
    }

    private Context(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game) {
        this(sideToPlay, adversity, game, new Stack<MoveInterface>());
    }

    public Context(final SideInterface sideToPlay, final AdversityInterface adversity, final GameInterface game, final Stack<MoveInterface> history) {
        ++instances;
        //contexte actuel = f(contexte initial, sommes des options choisies)
        this.sideToPlay = sideToPlay;
        this.game = game;
        this.adversity = adversity;
        this.history = history;
        //TODO ajouter une méthode replay et rewind dans ContextManager        
    }

    @Override
    public Stack<MoveInterface> history() {
        return this.history;
    }

    private ContextInterface apply(final Stack<MoveInterface> history) {
        return new Context(this.side(), this.adversity(), this.game(), history);
    }

    @Override
    public ContextInterface apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public SideInterface side() {
        return this.sideToPlay;
    }

    @Override
    public ContextInterface apply(final SideInterface side) {
        // TODO this.side().equals(side)
        return this.side() == side ? this.apply() : new Context(side, this.adversity(), this.game(), this.history());
        //return new Context(side, this.adversity(), this.game(), this.history());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public GameInterface game() {
        return this.game;
    }

    @Override
    public ContextInterface apply(final GameInterface game) {
        // TODO this.game().equals(game)
        return this.game() == game ? this.apply() : new Context(this.side(), this.adversity(), game, this.history());
    }

    @Override
    public ContextInterface apply(final GameInterface game, final Stack<MoveInterface> history) {
        return new Context(this.side(), this.adversity(), game, history);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public AdversityInterface adversity() {
        return this.adversity;
    }

    @Override
    public ContextInterface apply(final AdversityInterface adversity) {
        return new Context(this.side(), adversity, this.game(), this.history());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isOver() {
        return this.game().isOver(this);
    }

    /*
    @Override
    public List<MoveTypeInterface> playableMoves() {
        return this.game().playableMoves(this.side());
    }
    */

    @Override
    public ContextInterface apply(final MoveInterface choice) { // TODO ? vérifier l'égalité du contexte
        return choice.isNull() ? this.apply(this.history.push(choice)) : this.apply(this.game().apply(choice), this.history.push(choice));
    }

    @Override
    public Double evaluation() {
        return this.game().referee().evaluate(this);
    }

    @Override
    public Double estimation() {
        return this.game().referee().estimate(this);
    }

    @Override
    public List<MoveInterface> options() {
        return this.game().referee().allowedOptions(this);
    }
    /*-------------------------------------8<-------------------------------------*/

}