package fr.designpattern.zerosumgames.core.ai.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public class OneSingleMoveExists implements IPredicate {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<IGameBoardMove> legalMoves) {
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
	public void onTrue(List<IGameBoardMove> legalMoves) {
		System.out.println("\nIl n y a qu'une seule option possible...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<IGameBoardMove> legalMoves) {
		System.out.println("\nIl y a " + legalMoves.size() + " options possibles...");
	}	
	//--------------------------------------------------------------------------------------
}