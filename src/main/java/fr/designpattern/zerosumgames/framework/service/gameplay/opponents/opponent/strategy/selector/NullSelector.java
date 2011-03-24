package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector;

import java.util.List;
import java.util.Random;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;

public class NullSelector implements SelectorInterface {

	private GameInterface context;
	public final void setContext(final GameInterface context) {
		this.context = context;
	}
	public final GameInterface getContext() {
		return this.context;
	}

	public LegalMoveInterface applySelection(final List<LegalMoveInterface> legalMoves) {
		return legalMoves.get(new Random().nextInt(legalMoves.size()));
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}