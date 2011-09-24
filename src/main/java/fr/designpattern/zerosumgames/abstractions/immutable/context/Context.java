
package fr.designpattern.zerosumgames.abstractions.immutable.context;

import java.util.List;

import scala.collection.immutable.Stack;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class Context implements ContextInterface {

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
    public ContextInterface apply(final SideInterface sideToPlay) {
        return new Context(sideToPlay, this.adversity(), this.game(), this.history());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public GameInterface game() {
        return this.game;
    }

    @Override
    public ContextInterface apply(final GameInterface game) {
        return new Context(this.side(), this.adversity(), game, this.history());
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
        return this.game().isGameOver(this);
    }

    @Override
    public List<MoveTypeInterface> playableMoves() {
        return this.game().playableMoves(this.side());
    }

    @Override
    public ContextInterface play(final MoveInterface move) {
        return this.apply(this.game().play(move), this.history.push(move));
    }

    @Override
    public Double getTerminalEvaluation() {
        return this.game().referee().getTerminalEvaluation(this);
    }

    @Override
    public Double getHeuristicEvaluation() {
        return this.game().referee().getHeuristicEvaluation(this);
    }

    /*-------------------------------------8<-------------------------------------*/

}