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

package abstractions.utils.math;

public class IntegersRange extends Range<Integer> implements IntegersRangeInterface {

    public IntegersRange(final int lowerBound, final int upperBound) {
        super(lowerBound, upperBound);
    }

    public final int getLowerBound() {
        return this.lowerBound;
    }

    public final int getUpperBound() {
        return this.upperBound;
    }

    public final int getCapacity() {
        return 1 + this.getUpperBound() - this.getLowerBound();
    }

    // TODO add to unit tests
    public static void main(final String[] args) {

        final IntegersRange range = new IntegersRange(1, 4);

        System.out.println(range.contains(1));
        System.out.println(range.contains(2));
        System.out.println(range.contains(3));
        System.out.println(range.contains(4));

        System.out.println(range.contains(5));

        final IntegersRange subRange = new IntegersRange(2, 3);

        System.out.println(range.contains(subRange));
    }

}