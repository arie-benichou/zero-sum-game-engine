package fr.designpattern.zerosumgames.core.ai.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public class WinningMoveExists implements IPredicate {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<IGameBoardMove> legalMoves) {
		//--------------------------------------------------------------------------------------		
		boolean predicate = false;
		//--------------------------------------------------------------------------------------		
		for(IGameBoardMove move: legalMoves) {
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
	public void onTrue(List<IGameBoardMove> legalMoves) {
		System.out.println("\nVictoire imminente détectée pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<IGameBoardMove> legalMoves) {
		System.out.println("\nPas de victoire imminente pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
}