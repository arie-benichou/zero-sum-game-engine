package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;

public interface SelectorInterface {
	
	LegalMoveInterface applySelection(List<LegalMoveInterface> legalMoves);

	void setContext(GameInterface context);
	GameInterface getContext();

}