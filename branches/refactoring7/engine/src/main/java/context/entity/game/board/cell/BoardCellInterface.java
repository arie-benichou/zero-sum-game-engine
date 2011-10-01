
package context.entity.game.board.cell;

import util.annotations.Application;
import util.annotations.Value;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.position.PositionInterface;

public interface BoardCellInterface extends CellInterface<PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Value
    PieceInterface value();

    @Value
    PositionInterface position();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Application
    BoardCellInterface apply();

    @Override
    @Application
    BoardCellInterface apply(PieceInterface piece);

    @Application
    BoardCellInterface apply(PositionInterface position);

    @Application
    BoardCellInterface apply(PositionInterface position, PieceInterface piece);

    /*-------------------------------------8<-------------------------------------*/

}