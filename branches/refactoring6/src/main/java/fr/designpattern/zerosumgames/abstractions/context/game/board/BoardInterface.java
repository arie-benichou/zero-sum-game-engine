
package fr.designpattern.zerosumgames.abstractions.context.game.board;

import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.move.mutation.MutationInterface;

public interface BoardInterface extends CellManagerInterface<PositionInterface, PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    int rows();

    int columns();

    int numberOfCells();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    BoardInterface apply();

    @Override
    // TODO ?? apply(Collection<BoardCellInterface> cells)
    public BoardInterface apply(MutationInterface<PositionInterface, PieceInterface> value);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    BoardCellInterface cell(PositionInterface address);

    BoardCellInterface cell(int row, int column);

    /*-------------------------------------8<-------------------------------------*/

    //Map<DirectionInterface, BoardCellInterface> neighbourhoodOf(PositionInterface position);

    /*-------------------------------------8<-------------------------------------*/

    int count(PieceInterface value); //TODO Map<PieceInterface, Integer> count(Set<PieceInterface> setOfValues);

    /*-------------------------------------8<-------------------------------------*/

}
