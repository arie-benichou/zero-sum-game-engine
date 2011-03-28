
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
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
    /*
     * protected double applyEvaluation(final LegalMoveInterface moveToEvaluate,
     * final int maximalDepth) { return this.applyEvaluation(moveToEvaluate,
     * maximalDepth, 1); }
     * 
     * //------------------------------------------------------------------------
     * -------------- protected double applyEvaluation(final LegalMoveInterface
     * moveToEvaluate) { return this.applyEvaluation(moveToEvaluate,
     * this.maximalDepth); }
     */
    //--------------------------------------------------------------------------------------
    public static class myCallable implements Callable<Double> {

        private final LegalMoveInterface moveToEvaluate;

        private final GameInterface localContext;

        private final int maximalDepth;

        /*
         * private final GameInterface getLocalContext() { return
         * this.localContext; }
         */

        public myCallable(final GameInterface localContext,
                final LegalMoveInterface moveToEvaluate, final int maximalDepth) {
            this.moveToEvaluate = moveToEvaluate;
            this.localContext = localContext;
            this.maximalDepth = maximalDepth;
        }

        /*
         * protected double applyEvaluation( final LegalMoveInterface
         * moveToEvaluate, final int maximalDepth) { return
         * this.applyEvaluation(moveToEvaluate, maximalDepth, 1); }
         * 
         * protected double applyEvaluation(final LegalMoveInterface
         * moveToEvaluate) { return this.applyEvaluation(moveToEvaluate,
         * this.maximalDepth); }
         */

        private double applyEvaluation(final GameInterface context,
                final LegalMoveInterface moveToEvaluate,
                final int profondeur, final double side) {
            double score;
            final OpponentsEnumeration nextPlayer = context
                    .computeNextSideToPlay(moveToEvaluate,
                            context.doMove(moveToEvaluate));

            ///System.out.println(this.getLocalContext());

            if (!OpponentsEnumeration.isAPlayer(nextPlayer) || profondeur == 1) {
                score = side
                        * context.computeStaticEvaluation(
                                moveToEvaluate);

                ///System.out.println("score = " + score);

            }
            else {
                final List<LegalMoveInterface> opponentMoves = context
                        .getLegalMoves(nextPlayer);
                for (final LegalMoveInterface opponentMove : opponentMoves) {
                    opponentMove.setEvaluation(this.applyEvaluation(
                            context,
                            opponentMove,
                            profondeur - 1, -side));
                }
                score = (side == 1) ? Collections.min(opponentMoves)
                        .getEvaluation() : Collections.max(opponentMoves)
                        .getEvaluation();
            }
            context.undoMove(moveToEvaluate);
            return score;
        }

        public Double call() {

            //System.out.println(this);
            //System.out.println(this.getLocalContext());

            return Double.valueOf(this.applyEvaluation(
                    this.localContext, this.moveToEvaluate,
                    this.maximalDepth, 1));

        }

    }

    //--------------------------------------------------------------------------------------
    @Override
    public List<LegalMoveInterface> applyEvaluation(
            final List<LegalMoveInterface> legalMoves) {

        final ExecutorService pool = Executors.newFixedThreadPool(legalMoves
                .size());

        final List<Future<Double>> list = new ArrayList<Future<Double>>(
                legalMoves.size());

        for (final LegalMoveInterface move : legalMoves) {

            final Callable<Double> callable = new myCallable(this.getContext()
                    .clone(), move, this.getMaximalDepth());
            final Future<Double> future = pool.submit(callable);
            list.add(future);
            //System.out.println(this.getContext());
        }

        //System.out.println("\n");

        int index = 0;
        for (final Future<Double> future : list) {
            try {
                legalMoves.get(index).setEvaluation((double) future.get());
                //System.out.println(legalMoves.get(index).debug());
                ++index;
            }
            catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (final ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //System.out.println("\n");

        return legalMoves;

    }

    //--------------------------------------------------------------------------------------

}