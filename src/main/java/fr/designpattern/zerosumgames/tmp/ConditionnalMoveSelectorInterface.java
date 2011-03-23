package fr.designpattern.zerosumgames.tmp;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;


public interface ConditionnalMoveSelectorInterface extends MoveSelectorInterface {
	
	boolean isApplicable(final GameInterface context, final List<LegalMoveInterface> legalMoves);
	LegalMoveInterface simplify(final GameInterface context, final List<LegalMoveInterface> legalMoves);
	
}