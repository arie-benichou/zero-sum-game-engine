/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.selector;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Human extends AbstractSelector {

    public Human() {
        super(false, false);
    }

    private MutationInterface askForSelection(final List<MutationInterface> mutations) {
        /* TODO créer un selecteur qui retourne l'unique mutation pour un singleton
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
            if (i < 1 || i > mutations.size())
                throw new InputMismatchException();
            else
                mutation = mutations.get(i - 1);
        }
        catch (final InputMismatchException e) {
            System.out.println("\nThere is no such move.");
            System.out.println("Please try again...");
            mutation = this.askForSelection(mutations);
        }
        return mutation;
    }

    @Override
    public List<MutationInterface> select(final TreeMap<Integer, List<MutationInterface>> evaluatedMutations) {
        return Lists.newArrayList(this.askForSelection(Lists.newArrayList(Iterables.concat(evaluatedMutations.values()))));
    }

}