package fr.designpattern.zerosumgames.framework.game.builder;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerInterface;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface BuilderInterface {

	Builder player1(final PlayerInterface player1);

	Builder player2(final PlayerInterface player2);

	Builder boardDimension(final DimensionInterface dimension);
	
	GameInterface build();
		
}