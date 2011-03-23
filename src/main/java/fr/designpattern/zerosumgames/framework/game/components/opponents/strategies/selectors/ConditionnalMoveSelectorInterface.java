package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;


public interface ConditionnalMoveSelectorInterface extends MoveSelectorInterface {
	
	boolean isApplicable(final GameInterface context, final List<MoveInterface> legalMoves);
	MoveInterface simplify(final GameInterface context, final List<MoveInterface> legalMoves);
	
}