package fr.designpattern.zerosumgames.framework.gameplay.game.builder;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.players.PlayerInterface;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface BuilderInterface {

	Builder player1(final PlayerInterface player1);

	Builder player2(final PlayerInterface player2);

	Builder boardDimension(final DimensionInterface dimension);
	
	GameInterface build();
		
}