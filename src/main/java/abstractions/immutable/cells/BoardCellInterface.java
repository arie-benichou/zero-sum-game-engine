
package abstractions.immutable.cells;

import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.positions.PositionInterface;

public interface BoardCellInterface extends CellInterface<PieceInterface> {

    @Override
    PieceInterface value();

    @Override
    BoardCellInterface apply();

    @Override
    BoardCellInterface apply(PieceInterface value);

    PositionInterface position();

    BoardCellInterface apply(PositionInterface position, PieceInterface piece);

}