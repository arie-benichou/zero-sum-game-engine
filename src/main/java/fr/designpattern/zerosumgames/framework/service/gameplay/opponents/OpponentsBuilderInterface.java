package fr.designpattern.zerosumgames.framework.service.gameplay.opponents;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public interface OpponentsBuilderInterface {

	OpponentsBuilderInterface player1(final OpponentInterface player1);

	OpponentsBuilderInterface player2(final OpponentInterface player2);
	
	OpponentsInterface build();
		
}