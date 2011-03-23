package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors.MoveSelectorPredicateInterface;

public class OneSingleMoveExists implements MoveSelectorPredicateInterface {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<LegalMoveInterface> legalMoves) {
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
	public void onTrue(List<LegalMoveInterface> legalMoves) {
		System.out.println("\nIl n y a qu'une seule option possible...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<LegalMoveInterface> legalMoves) {
		System.out.println("\nIl y a " + legalMoves.size() + " options possibles...");
	}	
	//--------------------------------------------------------------------------------------
}