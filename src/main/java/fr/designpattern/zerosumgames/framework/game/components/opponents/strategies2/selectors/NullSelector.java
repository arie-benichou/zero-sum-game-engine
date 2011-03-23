package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.selectors;

import java.util.List;
import java.util.Random;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public class NullSelector implements SelectorInterface {
	
	public MoveInterface applySelection(List<MoveInterface> legalMoves) {
		return legalMoves.get(new Random().nextInt(legalMoves.size()));
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
