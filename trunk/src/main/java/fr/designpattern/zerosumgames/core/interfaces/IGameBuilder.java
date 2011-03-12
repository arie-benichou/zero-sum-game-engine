package fr.designpattern.zerosumgames.core.interfaces;

import fr.designpattern.zerosumgames.core.GameBuilder;

// TODO rajouter final lorsqu'applicable dans les autres interfaces
public interface IGameBuilder {

	GameBuilder player1(final IGamePlayer player1);

	GameBuilder player2(final IGamePlayer player2);

	GameBuilder boardDimension(final IGameBoardDimension dimension);
	
	IGame build();
		
}