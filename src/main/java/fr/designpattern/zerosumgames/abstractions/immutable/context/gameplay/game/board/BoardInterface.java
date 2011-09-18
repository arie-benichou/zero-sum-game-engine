
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.direction.DirectionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.MutationInterface;

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
