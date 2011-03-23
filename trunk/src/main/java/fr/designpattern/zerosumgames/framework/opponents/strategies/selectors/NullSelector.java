package fr.designpattern.zerosumgames.framework.opponents.strategies.selectors;

import java.util.List;
import java.util.Random;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public class NullSelector implements SelectorInterface {
	
	private GameInterface context;
	public final void setContext(GameInterface context) {
		this.context = context;
	}
	public final GameInterface getContext() {
		return this.context;
	}
	
	public MoveInterface applySelection(List<MoveInterface> legalMoves) {
		return legalMoves.get(new Random().nextInt(legalMoves.size()));
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}