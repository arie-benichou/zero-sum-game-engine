package fr.designpattern.zerosumgames.framework.build.game.components.opponents.strategies.evaluators;

import fr.designpattern.zerosumgames.framework.build.game.IGame;
import fr.designpattern.zerosumgames.framework.build.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.build.game.components.move.IGameMoveEvaluator;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.GamePlayersEnumeration;


public class MiniMaxMoveEvaluator implements IGameMoveEvaluator{

	//--------------------------------------------------------------------------------------
	private transient int maximalDepth;
	public final int getMaximalDepth() {
		return maximalDepth;
	}
	public final void setMaximalDepth(final int maximalDepth) {
		this.maximalDepth = maximalDepth;
	}
	//--------------------------------------------------------------------------------------	
	private transient IGame context;
	protected final IGame getContext() {
		return context;
	}	
	private final void setContext(final IGame context) {
		this.context = context;
	}
	//--------------------------------------------------------------------------------------	
	public MiniMaxMoveEvaluator(final int maximaDepth) {
		this.setMaximalDepth(maximaDepth);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final IGame context, final IGameMove moveToEvaluate, final int maximalDepth) {
		this.setContext(context);
		return this.evaluate(moveToEvaluate, maximalDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final IGame context, final IGameMove moveToEvaluate) {
		return this.evaluate(context, moveToEvaluate, this.maximalDepth);
	}
	//--------------------------------------------------------------------------------------	
	protected double evaluate(final IGameMove moveToEvaluate, final int profondeur, double alpha, double beta, final double side) {

		double score;
		final GamePlayersEnumeration nextPlayer = this.getContext().whoShallPlay(moveToEvaluate, this.getContext().doMove(moveToEvaluate));
		
		if(!GamePlayersEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToEvaluate); 
		}
		else if(side == 1) {
			for(IGameMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToEvaluate)) {
				beta = Math.min(beta, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = beta;
		}
		else {
			for(IGameMove opponentMove : this.getContext().getLegalMoves(nextPlayer, moveToEvaluate)) {
				alpha = Math.max(alpha, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = alpha;
		}
		this.getContext().undoMove(moveToEvaluate);
		return score;
		
	}
	//--------------------------------------------------------------------------------------
}