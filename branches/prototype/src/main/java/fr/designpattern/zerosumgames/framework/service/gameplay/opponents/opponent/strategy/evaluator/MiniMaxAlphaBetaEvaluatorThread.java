
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.concurrent.Callable;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class MiniMaxAlphaBetaEvaluatorThread implements Callable<Double> {

    private int alphabetacutoffs;
    //--------------------------------------------------------------------------------------
    private final transient int maximalDepth;
    private volatile LegalMoveInterface moveToEvaluate;
    //--------------------------------------------------------------------------------------
    private final GameInterface localContext;

    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    //--------------------------------------------------------------------------------------
    public MiniMaxAlphaBetaEvaluatorThread(final GameInterface localContext,
            final LegalMoveInterface moveToEvaluate, final int maximalDepth) {
        this.moveToEvaluate = moveToEvaluate;
        this.localContext = localContext;
        this.maximalDepth = maximalDepth;
    }

    //--------------------------------------------------------------------------------------
    private double applyEvaluation(final LegalMoveInterface moveToEvaluate, final int profondeur, final double side, double alpha, double beta) {
        double score;

        final OpponentsEnumeration nextPlayer = this.localContext.computeNextSideToPlay(moveToEvaluate, this.localContext.doMove(moveToEvaluate));

        if (!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {

            //System.out.println(this.localContext);

            score = side * this.localContext.computeStaticEvaluation(moveToEvaluate);
        }
        else {

            if (side == 1) {
                // TODO créer le selector BestAlphaBeta
                for (final LegalMoveInterface opponentMove : this.localContext.getLegalMoves(nextPlayer)) {
                    beta = Math.min(beta, this.applyEvaluation(opponentMove, profondeur - 1, -side, alpha, beta));
                    if (alpha >= beta) { // élagage alpha/beta : l'adversaire a trouvé un meilleur "pire coup"
                        ++this.alphabetacutoffs;
                        break;
                    }
                }
                score = beta;
            }
            else {
                // TODO créer le selector BestAlphaBeta
                for (final LegalMoveInterface opponentMove : this.localContext.getLegalMoves(nextPlayer)) {
                    alpha = Math.max(alpha, this.applyEvaluation(opponentMove, profondeur - 1, -side, alpha, beta));
                    if (alpha >= beta) { // élagage alpha/beta : le joueur a trouvé un meilleur "meilleur coup"
                        ++this.alphabetacutoffs;
                        break;
                    }
                }
                score = alpha;
            }

        }

        this.localContext.undoMove(moveToEvaluate);

        return score;
    }

    //--------------------------------------------------------------------------------------
    public Double call() throws Exception {
        //System.out.println("\nDébut de l'évaluation du coup " + this.moveToEvaluate);
        final Double result = Double.valueOf(this
                .applyEvaluation(this.moveToEvaluate, this.maximalDepth, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        System.out.println("\nFin de l'évaluation du coup " + this.moveToEvaluate);
        System.out.println("\nScore :  " + result);
        System.out.println("Nombre de coupures alpha/beta " + this.alphabetacutoffs + "\n");
        return result;
    }
    //--------------------------------------------------------------------------------------
}