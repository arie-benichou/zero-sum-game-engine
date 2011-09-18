package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public class GamePlayService implements GamePlayServiceInterface {

	private final GameServiceInterface gameService;

	public static GamePlayServiceInterface from(final GameServiceInterface gameService) {
		return new GamePlayService(gameService);
	}

	private GamePlayService(final GameServiceInterface gameService) {
		this.gameService = gameService;
	}

	@Override
	public boolean isGameOver(final GamePlayInterface gamePlay) {
		return this.gameService.isGameOver(gamePlay.game(), gamePlay.side());
	}

	@Override
	public List<MoveTypeInterface> playableMoves(final GamePlayInterface gamePlay) {
		return this.gameService.playableMoves(gamePlay.game(), gamePlay.side());
	}

	@Override
	public GamePlayInterface play(final GamePlayInterface gamePlay, final MoveInterface move) {
		final GameInterface game = this.gameService.play(gamePlay.game(), move);
		return gamePlay.apply(game); // TODO ?? changer de side ici
	}

}