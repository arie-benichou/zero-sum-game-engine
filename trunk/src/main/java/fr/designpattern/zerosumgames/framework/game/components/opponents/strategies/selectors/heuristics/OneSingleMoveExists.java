package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorPredicateInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public class OneSingleMoveExists implements MoveSelectorPredicateInterface {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<MoveInterface> legalMoves) {
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
	public void onTrue(List<MoveInterface> legalMoves) {
		System.out.println("\nIl n y a qu'une seule option possible...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<MoveInterface> legalMoves) {
		System.out.println("\nIl y a " + legalMoves.size() + " options possibles...");
	}	
	//--------------------------------------------------------------------------------------
}