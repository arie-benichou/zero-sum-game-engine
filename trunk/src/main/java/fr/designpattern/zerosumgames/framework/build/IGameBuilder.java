package fr.designpattern.zerosumgames.framework.build;

import fr.designpattern.zerosumgames.game.IGame;
import fr.designpattern.zerosumgames.game.components.board.dimension.IGameBoardDimension;
import fr.designpattern.zerosumgames.game.components.opponents.IGamePlayer;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface IGameBuilder {

	GameBuilder player1(final IGamePlayer player1);

	GameBuilder player2(final IGamePlayer player2);

	GameBuilder boardDimension(final IGameBoardDimension dimension);
	
	IGame build();
		
}