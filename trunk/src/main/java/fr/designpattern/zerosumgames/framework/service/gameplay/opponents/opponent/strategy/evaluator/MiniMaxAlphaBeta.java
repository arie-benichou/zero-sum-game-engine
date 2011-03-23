package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class MiniMaxAlphaBeta extends MiniMaxEvaluator {

	public MiniMaxAlphaBeta(int maximaDepth) {
		super(maximaDepth);
	}
	
	//--------------------------------------------------------------------------------------
	@Override
	protected double evaluate(final LegalMoveInterface moveToPlay, int profondeur, double alpha, double beta, double side) {

		double score;
		OpponentsEnumeration nextPlayer = this.getContext().whoShallPlay(moveToPlay, this.getContext().doMove(moveToPlay));
		
		if(!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToPlay); 
		}
		else if(side == 1) {
			for(LegalMoveInterface opponentMove : this.getContext().getLegalMoves(nextPlayer)) {
				beta = Math.min(beta, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
				//-------------------------------------------------------------------				
				// elagage alpha/beta : l'adversaire a trouvé un meilleur "pire coup"
				if(alpha >= beta) {
					break;
				}
				//-------------------------------------------------------------------				
			}
			score = beta;
		}
		else {
			for(LegalMoveInterface opponentMove : this.getContext().getLegalMoves(nextPlayer)) {
				alpha = Math.max(alpha, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
				//-------------------------------------------------------------------				
				// elagage alpha/beta : le joueur a trouvé un meilleur "meilleur coup"
				if(alpha >= beta) {
					break;
				}
				//-------------------------------------------------------------------				
			}
			score = alpha;
		}
		this.getContext().undoMove(moveToPlay);
		return score;
		
	}
	//--------------------------------------------------------------------------------------	

}