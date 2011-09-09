
package abstractions.immutable.cells;

import java.util.Map;

import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.positions.PositionInterface;

public interface BoardInterface extends CellManagerInterface<PositionInterface, PieceInterface> {

    @Override
    public BoardInterface apply();

    @Override
    public BoardInterface apply(Map<PositionInterface, PieceInterface> value);

    @Override
    public BoardCellInterface cell(PositionInterface address);

}
