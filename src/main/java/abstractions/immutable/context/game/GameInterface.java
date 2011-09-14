
package abstractions.immutable.context.game;

import java.util.List;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

public interface GameInterface extends ImmutableInterface<GameInterface> {

    BoardInterface board();

    GameInterface apply(BoardInterface board);

    RefereeInterface referee();

    GameInterface apply(RefereeInterface referee);

    //GameInterface apply(SideInterface side, MoveInterface move);

    GameInterface apply(MoveInterface move);

    //List<MoveTypeInterface> computePlayableMoves(BoardInterface board, SideInterface side);

    List<MoveTypeInterface> computePlayableMoves(SideInterface sideToPlay);

}
