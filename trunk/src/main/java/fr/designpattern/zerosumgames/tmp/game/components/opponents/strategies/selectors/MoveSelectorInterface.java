package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface MoveSelectorInterface {
	
	LegalMoveInterface select(GameInterface context, List<LegalMoveInterface> legalMoves);
}