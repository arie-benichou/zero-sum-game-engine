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

package abstractions.immutable.selection;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import annotations.Immutable;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Immutable
public class HumanConsole<I> implements SelectorInterface<I> {

    private I askForSelection(final List<I> evaluatedItems) {
        I selectedItem;
        int n = 0;
        final int numberOfDigits = (int) Math.log10(Math.abs(evaluatedItems.size())) + 1;
        System.out.println("\nPlease, choose an option :");
        for (final I item : evaluatedItems)
            System.out.format(" %0" + numberOfDigits + "d: %s\n", ++n, item);
        System.out.println("\nWhat is your move ?");
        final Scanner scanner = new Scanner(System.in);
        int i = 0;
        try {
            i = scanner.nextInt();
            if (i < 1 || i > evaluatedItems.size())
                throw new InputMismatchException();
            else
                selectedItem = evaluatedItems.get(i - 1);
        }
        catch (final InputMismatchException e) {
            System.out.println("\nThere is no such option.");
            System.out.println("Please try again...");
            selectedItem = this.askForSelection(evaluatedItems);
        }
        return selectedItem;
    }

    @Override
    public List<I> select(final TreeMap<?, List<I>> evaluatedItems) {
        final ArrayList<I> selectedItem = Lists.newArrayList();
        selectedItem.add(this.askForSelection(Lists.newArrayList(Iterables.concat(evaluatedItems.values()))));
        return selectedItem;
    }

}