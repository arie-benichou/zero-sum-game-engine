package fr.designpattern.zerosumgames.core.strategies.selectors;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

public class MiniMaxAlphaBetaMoveSelector extends MiniMaxMoveSelector {

	public MiniMaxAlphaBetaMoveSelector(int maximaDepth) {
		super(maximaDepth);
	}
	
	//--------------------------------------------------------------------------------------
	@Override
	protected double evaluateDeeply(final IGameBoardMove moveToPlay, int profondeur, double alpha, double beta, double side) {

		double score;
		GamePlayersEnumeration nextPlayer = this.getContext().whoShallPlay(moveToPlay, this.getContext().doMove(moveToPlay));
		
		if(!GamePlayersEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToPlay); 
		}
		else if(side == 1) {
			for(IGameBoardMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToPlay)) {
				beta = Math.min(beta, this.evaluateDeeply(opponentMove, profondeur - 1, alpha, beta, -side));
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
			for(IGameBoardMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToPlay)) {
				alpha = Math.max(alpha, this.evaluateDeeply(opponentMove, profondeur - 1, alpha, beta, -side));
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