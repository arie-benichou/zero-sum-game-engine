package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface SelectorInterface {
	
	LegalMoveInterface applySelection(List<LegalMoveInterface> legalMoves);

	void setContext(GameInterface context);
	GameInterface getContext();

}