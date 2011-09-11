
package abstractions.immutable.board;

import java.util.Map;

import abstractions.immutable.board.cell.BoardCellInterface;
import abstractions.immutable.board.cell.piece.PieceInterface;
import abstractions.immutable.board.cell.position.PositionInterface;

public interface BoardInterface extends CellManagerInterface<PositionInterface, PieceInterface> {

    @Override
    public BoardInterface apply();

    @Override
    public BoardInterface apply(Map<PositionInterface, PieceInterface> value);

    @Override
    public BoardCellInterface cell(PositionInterface address);

}
