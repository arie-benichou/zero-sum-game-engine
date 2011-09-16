
package abstractions.immutable.context.gameplay;

import java.util.List;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.gameplay.adversity.AdversityInterface;
import abstractions.immutable.context.gameplay.game.GameInterface;
import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

public interface GamePlayInterface extends ImmutableInterface<GamePlayInterface> {

    SideInterface sideToPlay();

    GamePlayInterface apply(SideInterface sideToPlay);

    AdversityInterface adversity();

    GamePlayInterface apply(AdversityInterface adversity);

    GameInterface game();

    GamePlayInterface apply(GameInterface game);

    //GamePlayInterface apply(SideInterface side, MoveInterface move);

    GamePlayInterface apply(MoveInterface move);

    //List<MoveTypeInterface> computePlayableMoves(SideInterface side, BoardInterface board);

    List<MoveTypeInterface> computePlayableMoves();

    public BoardInterface board();

}
