package fr.designpattern.zerosumgames.framework.game.components.move.evaluators;

import fr.designpattern.zerosumgames.framework.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayersEnumeration;

public class MiniMaxAlphaBetaMoveEvaluator extends MiniMaxMoveEvaluator {

	public MiniMaxAlphaBetaMoveEvaluator(int maximaDepth) {
		super(maximaDepth);
	}
	
	//--------------------------------------------------------------------------------------
	@Override
	protected double evaluate(final IGameMove moveToPlay, int profondeur, double alpha, double beta, double side) {

		double score;
		GamePlayersEnumeration nextPlayer = this.getContext().whoShallPlay(moveToPlay, this.getContext().doMove(moveToPlay));
		
		if(!GamePlayersEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToPlay); 
		}
		else if(side == 1) {
			for(IGameMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToPlay)) {
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
			for(IGameMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToPlay)) {
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