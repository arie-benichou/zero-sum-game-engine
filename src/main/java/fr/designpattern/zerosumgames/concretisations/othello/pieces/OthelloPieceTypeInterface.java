
package fr.designpattern.zerosumgames.concretisations.othello.pieces;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.direction.DirectionInterface;

public interface OthelloPieceTypeInterface extends ImmutableInterface<OthelloPieceTypeInterface> {

	boolean hasApplication(SideInterface side, BoardInterface board, PositionInterface position); // TODO ajouter à une ConcretePieceTypeInterface

	boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

}