package fr.designpattern.zerosumgames.framework.gameplay.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface SelectorInterface {
	
	MoveInterface applySelection(List<MoveInterface> legalMoves);

	void setContext(GameInterface context);
	GameInterface getContext();

}