package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public final class OneSingleMoveHeuristic extends AbstractConditionnalMoveHeuristic {

	public OneSingleMoveHeuristic(final MoveSelectorInterface nestedSelector) {
		super(nestedSelector);
	}

	public final LegalMoveInterface simplify(final GameInterface context, final List<LegalMoveInterface> legalMoves) {
		return legalMoves.get(0);
	}
	
	public final boolean isApplicable(final GameInterface context, final List<LegalMoveInterface> legalMoves) {
		System.out.println("Un seul coup possible, il est joué...");
		return legalMoves.size() == 1;
	}
	
}