package fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.evaluators;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMoveEvaluator;
import fr.designpattern.zerosumgames.framework.game.components.opponents.OpponentsEnumeration;


public class MiniMax implements IGameMoveEvaluator{

	//--------------------------------------------------------------------------------------
	private transient int maximalDepth;
	public final int getMaximalDepth() {
		return maximalDepth;
	}
	public final void setMaximalDepth(final int maximalDepth) {
		this.maximalDepth = maximalDepth;
	}
	//--------------------------------------------------------------------------------------	
	private transient GameInterface context;
	protected final GameInterface getContext() {
		return context;
	}	
	private final void setContext(final GameInterface context) {
		this.context = context;
	}
	//--------------------------------------------------------------------------------------	
	public MiniMax(final int maximaDepth) {
		this.setMaximalDepth(maximaDepth);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final GameInterface context, final MoveInterface moveToEvaluate, final int maximalDepth) {
		this.setContext(context);
		return this.evaluate(moveToEvaluate, maximalDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final GameInterface context, final MoveInterface moveToEvaluate) {
		return this.evaluate(context, moveToEvaluate, this.maximalDepth);
	}
	//--------------------------------------------------------------------------------------	
	protected double evaluate(final MoveInterface moveToEvaluate, final int profondeur, double alpha, double beta, final double side) {

		double score;
		final OpponentsEnumeration nextPlayer = this.getContext().whoShallPlay(moveToEvaluate, this.getContext().doMove(moveToEvaluate));
		
		if(!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToEvaluate); 
		}
		else if(side == 1) {
			for(MoveInterface opponentMove : this.getContext().getLegalMoves(nextPlayer)) {
				beta = Math.min(beta, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = beta;
		}
		else {
			for(MoveInterface opponentMove : this.getContext().getLegalMoves(nextPlayer)) {
				alpha = Math.max(alpha, this.evaluate(opponentMove, profondeur - 1, alpha, beta, -side));
			}
			score = alpha;
		}
		this.getContext().undoMove(moveToEvaluate);
		return score;
		
	}
	//--------------------------------------------------------------------------------------
}