package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent;


import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.StrategyInterface;

public interface OpponentInterface {

	void setContext(final GameInterface context);
	
	GameInterface getContext();	
	
	PlayerInterface getPlayer();
	
	StrategyInterface getStrategy();
	
	LegalMoveInterface selectMove(List<LegalMoveInterface> legalMoves);

}