
package abstractions.immutable.board.cell;

import abstractions.immutable.board.cell.piece.PieceInterface;
import abstractions.immutable.board.cell.position.PositionInterface;

public interface BoardCellInterface extends CellInterface<PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    BoardCellInterface apply();

    BoardCellInterface apply(PositionInterface position, PieceInterface piece);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    BoardCellInterface apply(PieceInterface piece);

    @Override
    PieceInterface value();

    /*-------------------------------------8<-------------------------------------*/

    BoardCellInterface apply(PositionInterface position);

    PositionInterface position();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    int hashCode();

    @Override
    boolean equals(Object object);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    String toString();

    /*-------------------------------------8<-------------------------------------*/

}