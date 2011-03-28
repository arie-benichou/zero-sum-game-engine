
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    public static class WordLengthCallable implements Callable<Integer> {

        private final String word;

        public WordLengthCallable(final String word) {
            this.word = word;
        }

        public Integer call() {
            return Integer.valueOf(this.word.length());
        }

    }

    public static void main(final String args[]) throws Exception {

        final String[] tab = { "Hello", " ", "world", "!" };
        final Set<Future<Integer>> set = new HashSet<Future<Integer>>();

        final ExecutorService pool = Executors.newFixedThreadPool(tab.length);

        for (final String word : tab) {

            final Callable<Integer> callable = new WordLengthCallable(word);
            final Future<Integer> future = pool.submit(callable);

            set.add(future);

        }

        int sum = 0;
        for (final Future<Integer> future : set) {
            sum += future.get();
        }

        System.out.printf("The sum of lengths is %s%n", sum);
        System.exit(sum);

    }
}