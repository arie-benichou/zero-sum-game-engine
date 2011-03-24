package fr.designpattern.zerosumgames.trash.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.trash.ConditionnalMoveSelectorInterface;
import fr.designpattern.zerosumgames.trash.MoveSelectorInterface;

public abstract class AbstractConditionnalMoveHeuristic implements ConditionnalMoveSelectorInterface {

	private final MoveSelectorInterface nestedSelector;

	public AbstractConditionnalMoveHeuristic(final MoveSelectorInterface nestedSelector) {
		this.nestedSelector = nestedSelector;
	}

	public final LegalMoveInterface select(final GameInterface context, final List<LegalMoveInterface> legalMoves) {
		final LegalMoveInterface move;
		if (this.isApplicable(context, legalMoves)) {
			move = this.simplify(context, legalMoves);
		}
		else {
			move = this.nestedSelector.select(context, legalMoves);	
		}
		return move;
	}

	public abstract LegalMoveInterface simplify(final GameInterface context, final List<LegalMoveInterface> legalMoves);
	
	public abstract boolean isApplicable(final GameInterface context, final List<LegalMoveInterface> legalMoves);

}