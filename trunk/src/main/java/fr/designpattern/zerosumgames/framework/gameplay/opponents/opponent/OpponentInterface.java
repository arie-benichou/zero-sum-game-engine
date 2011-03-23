package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent;


import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.StrategyInterface;

public interface OpponentInterface {

	void setContext(final GameInterface context);
	
	GameInterface getContext();	
	
	PlayerInterface getPlayer();
	
	StrategyInterface getStrategy();
	
	LegalMoveInterface selectMove(List<LegalMoveInterface> legalMoves);

}