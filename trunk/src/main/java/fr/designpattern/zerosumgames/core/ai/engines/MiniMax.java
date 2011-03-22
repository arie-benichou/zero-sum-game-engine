package fr.designpattern.zerosumgames.core.ai.engines;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;


public class MiniMax implements IAlgorithm {

	//--------------------------------------------------------------------------------------
	private transient int maximalDepth;
	public final int getMaximalDepth() {
		return maximalDepth;
	}
	public final void setMaximalDepth(int maximalDepth) {
		this.maximalDepth = maximalDepth;
	}
	//--------------------------------------------------------------------------------------	
	private transient IGame context;
	protected final IGame getContext() {
		return context;
	}	
	private final void setContext(IGame context) {
		this.context = context;
	}
	//--------------------------------------------------------------------------------------	
	public MiniMax(int maximaDepth) {
		this.setMaximalDepth(maximaDepth);
	}
	//--------------------------------------------------------------------------------------
	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToEvaluate, int maximalDepth) {
		this.setContext(context);
		return this.evaluateDeeply(moveToEvaluate, maximalDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
	}
	//--------------------------------------------------------------------------------------
	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToEvaluate) {
		return this.evaluateDeeply(context, moveToEvaluate, this.maximalDepth);
	}
	//--------------------------------------------------------------------------------------	
	protected double evaluateDeeply(final IGameBoardMove moveToEvaluate, int profondeur, double alpha, double beta, double side) {

		double score;
		GamePlayersEnumeration nextPlayer = this.getContext().whoShallPlay(moveToEvaluate, this.getContext().doMove(moveToEvaluate));
		
		if(!GamePlayersEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToEvaluate); 
		}
		else if(side == 1) {
			for(IGameBoardMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToEvaluate)) {
				beta = Math.min(beta, this.evaluateDeeply(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = beta;
		}
		else {
			for(IGameBoardMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToEvaluate)) {
				alpha = Math.max(alpha, this.evaluateDeeply(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = alpha;
		}
		this.getContext().undoMove(moveToEvaluate);
		return score;
		
	}
	//--------------------------------------------------------------------------------------
}