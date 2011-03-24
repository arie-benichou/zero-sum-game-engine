
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class MiniMaxAlphaBetaEvaluator extends MiniMaxEvaluator {

    //--------------------------------------------------------------------------------------
    private transient int alphabetacutoffs;

    //--------------------------------------------------------------------------------------
    public MiniMaxAlphaBetaEvaluator(final int maximaDepth) {
        super(maximaDepth);
    }

    //--------------------------------------------------------------------------------------
    @Override
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate,
            final int maximalDepth) {
        return this.applyEvaluation(moveToEvaluate, maximalDepth, 1,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    //--------------------------------------------------------------------------------------
    @Override
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate) {
        return this.applyEvaluation(moveToEvaluate, this.getMaximalDepth());
    }

    //--------------------------------------------------------------------------------------
    protected double applyEvaluation(final LegalMoveInterface moveToEvaluate,
            final int profondeur, final double side, double alpha, double beta) {
        double score;
        final OpponentsEnumeration nextPlayer = this.getContext()
                .computeNextSideToPlay(moveToEvaluate,
                        this.getContext().doMove(moveToEvaluate));

        //System.out.println(this.getContext());

        if (!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
            score = side
                    * this.getContext().computeStaticEvaluation(moveToEvaluate);
        }
        else {
            if (side == 1) {
                // TODO créer le selector BestAlphaBeta
                for (final LegalMoveInterface opponentMove : this.getContext()
                        .getLegalMoves(nextPlayer)) {
                    beta = Math.min(beta, this.applyEvaluation(opponentMove,
                            profondeur - 1, -side, alpha, beta));
                    if (alpha >= beta) { // élagage alpha/beta : l'adversaire a trouvé un meilleur "pire coup"
                        ++this.alphabetacutoffs;
                        break;
                    }
                }
                score = beta;
            }
            else {
                // TODO créer le selector BestAlphaBeta
                for (final LegalMoveInterface opponentMove : this.getContext()
                        .getLegalMoves(nextPlayer)) {
                    alpha = Math.max(alpha, this.applyEvaluation(opponentMove,
                            profondeur - 1, -side, alpha, beta));
                    if (alpha >= beta) { // élagage alpha/beta : le joueur a trouvé un meilleur "meilleur coup"
                        ++this.alphabetacutoffs;
                        break;
                    }
                }
                score = alpha;
            }
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
            move.setDepth(this.getMaximalDepth());
        }

        System.out.println("alpha/beta cut-offs: " + this.alphabetacutoffs);

        return legalMoves;
    }
    //--------------------------------------------------------------------------------------
}