package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.BestLegalMoveSelector;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.WorstLegalMoveSelector;


public class MiniMaxEvaluator extends NullEvaluator {
	//--------------------------------------------------------------------------------------
	private transient int maximalDepth;
	public final int getMaximalDepth() {
		return maximalDepth;
	}
	public final void setMaximalDepth(final int maximalDepth) {
		this.maximalDepth = maximalDepth;
	}
	//--------------------------------------------------------------------------------------	
	protected final static SelectorInterface bestLegalMoveSelector = new BestLegalMoveSelector();
	private final static SelectorInterface worstLegalMoveSelector = new WorstLegalMoveSelector();
	//--------------------------------------------------------------------------------------	
	public MiniMaxEvaluator(final int maximaDepth) {
		this.setMaximalDepth(maximaDepth);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final GameInterface context, final LegalMoveInterface moveToEvaluate, final int maximalDepth) {
		this.setContext(context);
		return this.evaluate(moveToEvaluate, maximalDepth, 1);
	}
	//--------------------------------------------------------------------------------------
	public double evaluate(final GameInterface context, final LegalMoveInterface moveToEvaluate) {
		return this.evaluate(context, moveToEvaluate, this.maximalDepth);
	}
	//--------------------------------------------------------------------------------------	
	protected double evaluate(final LegalMoveInterface moveToEvaluate, final int profondeur, final double side) {
		double score;
		final OpponentsEnumeration nextPlayer = this.getContext().computeNextSideToPlay(moveToEvaluate, this.getContext().doMove(moveToEvaluate));
		if(!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
			score = side * this.getContext().evaluate(moveToEvaluate); 
		}
		else {
			List<LegalMoveInterface> opponentMoves = this.getContext().getLegalMoves(nextPlayer);
			for(LegalMoveInterface opponentMove : opponentMoves) {
				opponentMove.setEvaluation(this.evaluate(opponentMove, profondeur - 1, -side));
			}
			score = (side == 1) ? worstLegalMoveSelector.applySelection(opponentMoves).getEvaluation() : bestLegalMoveSelector.applySelection(opponentMoves).getEvaluation();
		}
		this.getContext().undoMove(moveToEvaluate);
		return score;
	}
	//--------------------------------------------------------------------------------------
}