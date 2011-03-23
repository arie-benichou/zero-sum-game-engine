package fr.designpattern.zerosumgames.framework.opponents.strategies.evaluators;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.evaluators.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.BestLegalMoveSelector;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.WorstLegalMoveSelector;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;


public class MiniMax implements EvaluatorInterface{

	//--------------------------------------------------------------------------------------
	private transient int maximalDepth;
	public final int getMaximalDepth() {
		return maximalDepth;
	}
	public final void setMaximalDepth(final int maximalDepth) {
		this.maximalDepth = maximalDepth;
	}
	//--------------------------------------------------------------------------------------	
	protected final static MoveSelectorInterface bestLegalMoveSelector = new BestLegalMoveSelector();
	private final static MoveSelectorInterface worstLegalMoveSelector = new WorstLegalMoveSelector();
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
		return this.evaluate(moveToEvaluate, maximalDepth, 1);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final GameInterface context, final MoveInterface moveToEvaluate) {
		return this.evaluate(context, moveToEvaluate, this.maximalDepth);
	}
	//--------------------------------------------------------------------------------------	
	protected double evaluate(final MoveInterface moveToEvaluate, final int profondeur, final double side) {

		double score;
		
		final OpponentsEnumeration nextPlayer = this.getContext().whoShallPlay(moveToEvaluate, this.getContext().doMove(moveToEvaluate));
		
		if(!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			
			score = side * this.getContext().evaluate(moveToEvaluate); 
		}
		
		else {
			
			List<MoveInterface> opponentMoves = this.getContext().getLegalMoves(nextPlayer);
			for(MoveInterface opponentMove : opponentMoves) {
				opponentMove.setEvaluation(this.evaluate(opponentMove, profondeur - 1, -side));
			}
			
			score = (side == 1) ?
				worstLegalMoveSelector.select(this.getContext(), opponentMoves).getEvaluation()
				:
				bestLegalMoveSelector.select(this.getContext(), opponentMoves).getEvaluation();			
		}
		
		this.getContext().undoMove(moveToEvaluate);
		return score;
		
	}
	//--------------------------------------------------------------------------------------
}