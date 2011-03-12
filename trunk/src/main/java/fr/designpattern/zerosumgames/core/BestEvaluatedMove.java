package fr.designpattern.zerosumgames.core;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public class BestEvaluatedMove {
	// ---------------------------------------------------------------------
	private IGameBoardMove move;
	public final IGameBoardMove getMove() {
		return move;
	}
	public final void setMove(final IGameBoardMove move) {
		this.move = move;
	}
	// ---------------------------------------------------------------------
	private double evaluation;
	// ---------------------------------------------------------------------
	public final double getEvaluation() {
		return evaluation;
	}
	public final void setEvaluation(final double evaluation) {
		this.evaluation = evaluation;
	}
	// ---------------------------------------------------------------------	
	@Override
	public String toString() {
		return "BestEvaluatedMove [move=" + move + ", evaluation=" + evaluation+ "]";
	}
	// ---------------------------------------------------------------------	
}