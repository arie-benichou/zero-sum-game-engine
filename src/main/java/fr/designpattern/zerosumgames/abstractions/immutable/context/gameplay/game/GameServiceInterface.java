package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface GameServiceInterface {

	boolean isGameOver(final GameInterface game, final SideInterface side);

	List<MoveTypeInterface> playableMoves(final GameInterface game, final SideInterface side);

	GameInterface play(final GameInterface game, final MoveInterface move);

}