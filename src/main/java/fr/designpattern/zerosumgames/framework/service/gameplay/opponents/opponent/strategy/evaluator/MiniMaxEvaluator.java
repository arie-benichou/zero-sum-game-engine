
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;

public class MiniMaxEvaluator extends NullEvaluator {

    //--------------------------------------------------------------------------------------
    private final transient int maximalDepth;

    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    //--------------------------------------------------------------------------------------    
    public MiniMaxEvaluator(final int maximaDepth) {
        super();
        this.maximalDepth = maximaDepth;
    }

    //--------------------------------------------------------------------------------------
    @Override
    public List<LegalMoveInterface> applyEvaluation(final List<LegalMoveInterface> legalMoves) {

        final ExecutorService pool = Executors.newFixedThreadPool(legalMoves.size());
        final List<Future<Double>> list = new ArrayList<Future<Double>>(legalMoves.size());

        for (final LegalMoveInterface move : legalMoves) {

            //final Callable<Double> callable = new MiniMaxEvaluatorThread(this.getContext().clone(), move, this.getMaximalDepth());
            final Callable<Double> callable = new MiniMaxAlphaBetaEvaluatorThread(this.getContext().clone(), move, this.getMaximalDepth());
            final Future<Double> future = pool.submit(callable);

            list.add(future);

            //break;

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
                e.printStackTrace();
            }
            catch (final ExecutionException e) {
                e.printStackTrace();
            }

        }

        //System.out.println("\n");

        return legalMoves;

    }

    //--------------------------------------------------------------------------------------
}