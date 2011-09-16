
package abstractions.immutable.context.gameplay.game;

import java.util.List;

import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.referee.RefereeInterface;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

public class Game implements GameInterface {

    private final BoardInterface board;
    private final RefereeInterface referee;

    @Override
    public GameInterface apply() {
        return this;
    }

    public static GameInterface from(final BoardInterface board, final RefereeInterface referee) {
        return new Game(board, referee);
    }

    private Game(final BoardInterface board, final RefereeInterface referee) {
        this.board = board;
        this.referee = referee;
    }

    @Override
    public GameInterface apply(final BoardInterface board) {
        return new Game(board, this.referee());
    }

    @Override
    public BoardInterface board() {
        return this.board;
    }

    @Override
    public RefereeInterface referee() {
        return this.referee;
    }

    @Override
    public GameInterface apply(final RefereeInterface referee) {
        return new Game(this.board(), referee);
    }

    @Override
    public GameInterface apply(final MoveInterface move) {
        final BoardInterface newBoard = this.board.apply(move.mutation());
        return this.apply(newBoard);
    }

    /*
    @Override
    public List<MoveTypeInterface> computePlayableMoves(final BoardInterface board, final SideInterface side) {
        return this.referee().computePlayableMoves(board, side);
    }
    */

    @Override
    public List<MoveTypeInterface> computePlayableMoves(final SideInterface sideToPlay) {
        return this.referee().computePlayableMoves(this.board(), sideToPlay);
    }

}
