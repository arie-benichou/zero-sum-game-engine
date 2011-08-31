
package abstractions.selection;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import abstractions.mutation.MutationInterface;

public class HumanMoveSelector implements SelectionInterface {

    @Override
    public MutationInterface applySelection(final List<MutationInterface> mutations) {

        // TODO créer un selecteur qui retourne l'unique mutation pour un singleton
        /*
        if (mutations.size() == 1)
            return mutations.get(0);
        */

        MutationInterface mutation;

        int n = 0;
        final int numberOfDigits = (int) Math.log10(Math.abs(mutations.size())) + 1;
        System.out.println("\nLegal moves :");
        for (final MutationInterface legalMove : mutations)
            System.out.format(" %0" + numberOfDigits + "d: %s\n", ++n, legalMove);
        System.out.println("\nWhat is your move ?");

        final Scanner scanner = new Scanner(System.in);

        int i = 0;
        try {
            i = scanner.nextInt();
            if (i < 1 || i > mutations.size()) {
                System.out.println("\nThere is no such move.");
                System.out.println("Please try again...");
                mutation = this.applySelection(mutations);
            }
            else
                mutation = mutations.get(i - 1);
            //System.out.println("\nYou have choosen to play: " + mutation);
        }
        catch (final InputMismatchException e) {
            System.out.println("\nPositive natural integer expected.");
            System.out.println("Please try again...");
            mutation = this.applySelection(mutations);
        }
        return mutation;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}