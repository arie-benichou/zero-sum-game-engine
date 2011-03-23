package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.ConditionnalMoveSelectorInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;

public abstract class AbstractConditionnalMoveHeuristic implements ConditionnalMoveSelectorInterface {

	private final MoveSelectorInterface nestedSelector;

	public AbstractConditionnalMoveHeuristic(final MoveSelectorInterface nestedSelector) {
		this.nestedSelector = nestedSelector;
	}

	public final MoveInterface select(final GameInterface context, final List<MoveInterface> legalMoves) {
		final MoveInterface move;
		if (this.isApplicable(context, legalMoves)) {
			move = this.simplify(context, legalMoves);
		}
		else {
			move = this.nestedSelector.select(context, legalMoves);	
		}
		return move;
	}

	public abstract MoveInterface simplify(final GameInterface context, final List<MoveInterface> legalMoves);
	
	public abstract boolean isApplicable(final GameInterface context, final List<MoveInterface> legalMoves);

}