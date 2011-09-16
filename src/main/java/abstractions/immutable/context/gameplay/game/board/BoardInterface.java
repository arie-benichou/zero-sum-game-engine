
package abstractions.immutable.context.gameplay.game.board;

import java.util.Map;

import abstractions.immutable.context.gameplay.game.board.cell.BoardCellInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.context.gameplay.game.board.direction.DirectionInterface;
import abstractions.immutable.move.mutation.MutationInterface;

public interface BoardInterface extends CellManagerInterface<PositionInterface, PieceInterface> {

    @Override
    BoardInterface apply();

    @Override
    public BoardInterface apply(MutationInterface<PositionInterface, PieceInterface> value);

    @Override
    BoardCellInterface cell(PositionInterface address);

    int rows();

    int columns();

    BoardCellInterface cell(int row, int column);

    Map<DirectionInterface, BoardCellInterface> neighbourhoodOf(PositionInterface position);

}
