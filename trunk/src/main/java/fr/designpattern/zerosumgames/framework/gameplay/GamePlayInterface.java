package fr.designpattern.zerosumgames.framework.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.OpponentInterface;


public interface GamePlayInterface {
	
	/*
	OpponentInterface getOpponents();
	GameInterface getContext();
	*/

	//Façades
	
	List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side);
	
	OpponentInterface getOpponentByOrder(final OpponentsEnumeration side);
	
	OpponentsEnumeration getSideToPlay();
		
	boolean isGamePlayOver();
	
	void play(LegalMoveInterface move);
}
