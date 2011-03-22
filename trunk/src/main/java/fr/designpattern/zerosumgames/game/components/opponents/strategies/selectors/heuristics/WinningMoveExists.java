package fr.designpattern.zerosumgames.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.game.components.move.IGameMoveSelectorPredicate;

public class WinningMoveExists implements IGameMoveSelectorPredicate {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<IGameMove> legalMoves) {
		//--------------------------------------------------------------------------------------		
		boolean predicate = false;
		//--------------------------------------------------------------------------------------		
		for(IGameMove move: legalMoves) {
			if(move.getEvaluation().equals(Double.POSITIVE_INFINITY)) {
				predicate = true;
				break;
			}
		}
		//--------------------------------------------------------------------------------------		
		if(predicate) {
			this.onTrue(legalMoves);
		}
		else {
			this.onFalse(legalMoves);
		}
		//--------------------------------------------------------------------------------------		
		return predicate;
		//--------------------------------------------------------------------------------------		
	}
	//--------------------------------------------------------------------------------------
	public void onTrue(List<IGameMove> legalMoves) {
		System.out.println("\nVictoire imminente détectée pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<IGameMove> legalMoves) {
		System.out.println("\nPas de victoire imminente pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
}