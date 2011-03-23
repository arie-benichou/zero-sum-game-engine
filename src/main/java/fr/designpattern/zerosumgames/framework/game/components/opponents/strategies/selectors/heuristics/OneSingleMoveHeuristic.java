package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;

public final class OneSingleMoveHeuristic extends AbstractConditionnalMoveHeuristic {

	public OneSingleMoveHeuristic(final MoveSelectorInterface nestedSelector) {
		super(nestedSelector);
	}

	public final MoveInterface simplify(final GameInterface context, final List<MoveInterface> legalMoves) {
		return legalMoves.get(0);
	}
	
	public final boolean isApplicable(final GameInterface context, final List<MoveInterface> legalMoves) {
		System.out.println("Un seul coup possible, il est joué...");
		return legalMoves.size() == 1;
	}
	
}