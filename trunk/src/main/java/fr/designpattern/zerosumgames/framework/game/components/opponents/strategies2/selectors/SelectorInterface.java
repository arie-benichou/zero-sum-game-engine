package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public interface SelectorInterface {
	
	MoveInterface applySelection(List<MoveInterface> legalMoves);

}