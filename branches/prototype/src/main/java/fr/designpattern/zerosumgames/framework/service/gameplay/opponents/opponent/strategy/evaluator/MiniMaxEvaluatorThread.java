
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class MiniMaxEvaluatorThread implements Callable<Double> {

    //--------------------------------------------------------------------------------------
    private final transient int maximalDepth;
    private volatile LegalMoveInterface moveToEvaluate;
    //--------------------------------------------------------------------------------------
    private final GameInterface localContext;

    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    //--------------------------------------------------------------------------------------
    public MiniMaxEvaluatorThread(final GameInterface localContext,
            final LegalMoveInterface moveToEvaluate, final int maximalDepth) {
        this.moveToEvaluate = moveToEvaluate;
        this.localContext = localContext;
        this.maximalDepth = maximalDepth;
    }

    //--------------------------------------------------------------------------------------
    private double applyEvaluation(final LegalMoveInterface moveToEvaluate, final int profondeur, final double side) {
        double score;

        final OpponentsEnumeration nextPlayer = this.localContext.computeNextSideToPlay(moveToEvaluate, this.localContext.doMove(moveToEvaluate));

        if (!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {

            //System.out.println(this.localContext);

            score = side * this.localContext.computeStaticEvaluation(moveToEvaluate);
        }
        else {

            ///System.out.println(profondeur);

            ///System.out.println(this.localContext);

            final List<LegalMoveInterface> opponentMoves = this.localContext.getLegalMoves(nextPlayer);

            ///System.out.println("coups légaux de l'adversaire:\n");

            /*
            for (final LegalMoveInterface opponentMove : opponentMoves) {
                System.out.println(opponentMove.debug());
            }

            System.out.println("\n----\n");

            score = 0;
            */

            for (final LegalMoveInterface opponentMove : opponentMoves) {
                //System.out.println(opponentMove);

                opponentMove.setEvaluation(this.applyEvaluation(opponentMove, profondeur - 1, -side));
            }
            score = (side == 1) ? Collections.min(opponentMoves).getEvaluation() : Collections.max(opponentMoves).getEvaluation();

        }

        this.localContext.undoMove(moveToEvaluate);

        return score;
    }

    //--------------------------------------------------------------------------------------
    public Double call() throws Exception {
        return Double.valueOf(this.applyEvaluation(this.moveToEvaluate, this.maximalDepth, 1));
    }
    //--------------------------------------------------------------------------------------
}