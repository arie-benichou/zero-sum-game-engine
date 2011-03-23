package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public interface MoveSelectorInterface {
	
	MoveInterface select(GameInterface context, List<MoveInterface> legalMoves);
}