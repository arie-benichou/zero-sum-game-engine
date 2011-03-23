package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;

public interface MoveSelectorInterface {
	
	LegalMoveInterface select(GameInterface context, List<LegalMoveInterface> legalMoves);
}