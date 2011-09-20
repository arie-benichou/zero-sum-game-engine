
package fr.designpattern.zerosumgames.concretisations.reversi.context.pieces;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.direction.DirectionInterface;

public interface ReversiPieceTypeInterface extends ImmutableInterface<ReversiPieceTypeInterface> {

	boolean hasApplication(SideInterface side, BoardInterface board, PositionInterface position); // TODO ajouter à une ConcretePieceTypeInterface

	boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

}