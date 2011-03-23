package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;

public interface SelectorInterface {
	
	LegalMoveInterface applySelection(List<LegalMoveInterface> legalMoves);

	void setContext(GameInterface context);
	GameInterface getContext();

}