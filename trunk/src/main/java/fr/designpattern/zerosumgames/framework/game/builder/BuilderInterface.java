package fr.designpattern.zerosumgames.framework.game.builder;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.IGameBoardDimension;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.IGamePlayer;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface BuilderInterface {

	Builder player1(final IGamePlayer player1);

	Builder player2(final IGamePlayer player2);

	Builder boardDimension(final IGameBoardDimension dimension);
	
	GameInterface build();
		
}