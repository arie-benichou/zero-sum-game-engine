package fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.heuristics;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMoveSelectorPredicate;

public class WinningMoveExists implements IGameMoveSelectorPredicate {
	//--------------------------------------------------------------------------------------
	public boolean checkPredicate(List<MoveInterface> legalMoves) {
		//--------------------------------------------------------------------------------------		
		boolean predicate = false;
		//--------------------------------------------------------------------------------------		
		for(MoveInterface move: legalMoves) {
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
	public void onTrue(List<MoveInterface> legalMoves) {
		System.out.println("\nVictoire imminente détectée pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
	public void onFalse(List<MoveInterface> legalMoves) {
		System.out.println("\nPas de victoire imminente pour " + legalMoves.get(0).getSide() + "...");
	}
	//--------------------------------------------------------------------------------------
}