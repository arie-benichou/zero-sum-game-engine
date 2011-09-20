
package fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;

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

}