package fr.designpattern.zerosumgames.framework.game.components.moves.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMoveSelectorPredicate;

public class OneSingleMoveExists implements IGameMoveSelectorPredicate {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<IGameMove> legalMoves) {
		//--------------------------------------------------------------------------------------
		boolean predicate = false;
		//--------------------------------------------------------------------------------------
		if(legalMoves.size() == 1) {
			predicate = true;
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
	}
	//--------------------------------------------------------------------------------------
	public void onTrue(List<IGameMove> legalMoves) {
		System.out.println("\nIl n y a qu'une seule option possible...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<IGameMove> legalMoves) {
		System.out.println("\nIl y a " + legalMoves.size() + " options possibles...");
	}	
	//--------------------------------------------------------------------------------------
}