package fr.designpattern.zerosumgames.framework.gameplay.game;

import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player.PlayerInterface;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface GameBuilderInterface {

	GameBuilder player1(final PlayerInterface player1);

	GameBuilder player2(final PlayerInterface player2);

	GameBuilder boardDimension(final DimensionInterface dimension);
	
	GameInterface build();
		
}