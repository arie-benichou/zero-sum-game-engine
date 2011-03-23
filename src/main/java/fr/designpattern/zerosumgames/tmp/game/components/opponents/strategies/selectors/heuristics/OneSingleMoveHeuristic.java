package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors.MoveSelectorInterface;

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