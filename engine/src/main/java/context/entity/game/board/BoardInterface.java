
package context.entity.game.board;

import util.annotations.Application;
import util.annotations.Computation;
import util.annotations.Value;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.mutation.MutationInterface;

public interface BoardInterface extends CellManagerInterface<PositionInterface, PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    int rows();

    @Value
    int columns();

    @Value
    int numberOfCells();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Application
    BoardInterface apply();

    @Override
    @Application
    public BoardInterface apply(MutationInterface<PositionInterface, PieceInterface> value);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Computation
    BoardCellInterface cell(PositionInterface address);

    @Computation
    BoardCellInterface cell(int row, int column);

    @Computation
    int count(PieceInterface value);

    /*-------------------------------------8<-------------------------------------*/

    //@Computation Map<DirectionInterface, BoardCellInterface> neighbourhoodOf(PositionInterface position);    

}