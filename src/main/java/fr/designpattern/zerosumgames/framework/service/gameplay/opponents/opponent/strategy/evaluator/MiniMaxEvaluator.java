
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.BestLegalMoveSelector;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.WorstLegalMoveSelector;

public class MiniMaxEvaluator extends NullEvaluator {

    //--------------------------------------------------------------------------------------
    private final transient int maximalDepth;

    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    //--------------------------------------------------------------------------------------
    protected static final SelectorInterface BEST_LEGAL_MOVE_SELECTOR = new BestLegalMoveSelector();
    protected static final SelectorInterface WORST_LEGAL_MOVE_SELECTOR = new WorstLegalMoveSelector();

    //--------------------------------------------------------------------------------------
    public MiniMaxEvaluator(final int maximaDepth) {
        super();
        this.maximalDepth = maximaDepth;
    }

    //--------------------------------------------------------------------------------------
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate,
            final int maximalDepth) {
        return this.applyEvaluation(moveToEvaluate, maximalDepth, 1);
    }

    //--------------------------------------------------------------------------------------
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate) {
        return this.applyEvaluation(moveToEvaluate, this.maximalDepth);
    }

    //--------------------------------------------------------------------------------------
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate,
            final int profondeur, final double side) {
        double score;
        final OpponentsEnumeration nextPlayer = this.getContext()
                .computeNextSideToPlay(moveToEvaluate,
                        this.getContext().doMove(moveToEvaluate));
        if (!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
            score = side
                    * this.getContext().computeStaticEvaluation(moveToEvaluate);
        }
        else {
            final List<LegalMoveInterface> opponentMoves = this.getContext()
                    .getLegalMoves(nextPlayer);
            for (final LegalMoveInterface opponentMove : opponentMoves) {
                opponentMove.setEvaluation(this.applyEvaluation(opponentMove,
                        profondeur - 1, -side));
            }
            score = (side == 1) ? MiniMaxEvaluator.WORST_LEGAL_MOVE_SELECTOR
                    .applySelection(opponentMoves).getEvaluation()
                    : MiniMaxEvaluator.BEST_LEGAL_MOVE_SELECTOR.applySelection(
                            opponentMoves).getEvaluation();
        }
        this.getContext().undoMove(moveToEvaluate);
        return score;
    }

    //--------------------------------------------------------------------------------------
    @Override
    public List<LegalMoveInterface> applyEvaluation(
            final List<LegalMoveInterface> legalMoves) {
        for (final LegalMoveInterface move : legalMoves) {
            move.setEvaluation(this.applyEvaluation(move));
            move.setDepth(this.maximalDepth);
        }
        return legalMoves;
    }
    //--------------------------------------------------------------------------------------
}