package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class GameService implements GameServiceInterface {

	public static GameServiceInterface from() {
		return new GameService();
	}

	private GameService() {}

	@Override
	public boolean isGameOver(final GameInterface game, final SideInterface side) {
		return game.referee().isGamePlayOver(game.board(), side);
	}

	@Override
	public List<MoveTypeInterface> playableMoves(final GameInterface game, final SideInterface side) {
		return game.referee().computePlayableMoves(game.board(), side);
	}

	@Override
	public GameInterface play(final GameInterface game, final MoveInterface move) {
		final BoardInterface board = game.board().apply(move.mutation()); // TODO passer directement les mutations ?
		return game.apply(board);
	}

}