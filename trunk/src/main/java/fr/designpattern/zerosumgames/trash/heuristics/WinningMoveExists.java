package fr.designpattern.zerosumgames.trash.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.trash.MoveSelectorPredicateInterface;

public class WinningMoveExists implements MoveSelectorPredicateInterface {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<LegalMoveInterface> legalMoves) {
		//--------------------------------------------------------------------------------------		
		boolean predicate = false;
		//--------------------------------------------------------------------------------------		
		for(LegalMoveInterface move: legalMoves) {
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
	public void onTrue(List<LegalMoveInterface> legalMoves) {
		System.out.println("\nVictoire imminente détectée pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<LegalMoveInterface> legalMoves) {
		System.out.println("\nPas de victoire imminente pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
}