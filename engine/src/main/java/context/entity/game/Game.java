
package context.entity.game;

import java.util.List;

import context.ContextInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.referee.RefereeInterface;
import context.event.MoveInterface;

public class Game implements GameInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final BoardInterface board;
    private final RefereeInterface referee;

    /*-------------------------------------8<-------------------------------------*/

    public static GameInterface from(final BoardInterface board, final RefereeInterface referee) {
        return new Game(board, referee);
    }

    private Game(final BoardInterface board, final RefereeInterface referee) {
        this.board = board;
        this.referee = referee;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public GameInterface apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public GameInterface apply(final BoardInterface board) {
        // TODO this.board().equals(board)
        return this.board() == board ? this.apply() : new Game(board, this.referee());
    }

    @Override
    public BoardInterface board() {
        return this.board;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public RefereeInterface referee() {
        return this.referee;
    }

    @Override
    public GameInterface apply(final RefereeInterface referee) {
        // TODO this.referee().equals(referee)
        return this.referee() == referee ? this.apply() : new Game(this.board(), referee);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public List<MoveInterface> options(final ContextInterface context) {
        return this.referee().allowedOptions(context);
    }

    @Override
    public GameInterface apply(final MoveInterface move) {
        return move.isNull() ? this.apply() : this.apply(this.board().apply(move.value().boardMutation()));
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isOver(final ContextInterface context) {
        return this.referee().isOver(context);
    }

}