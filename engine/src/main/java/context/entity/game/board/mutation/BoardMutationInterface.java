
package context.entity.game.board.mutation;

import java.util.Map;

import util.annotations.Application;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.position.PositionInterface;

public interface BoardMutationInterface extends MutationInterface<PositionInterface, PieceInterface> {

    @Application
    BoardMutationInterface apply(Map<PositionInterface, PieceInterface> value);

}