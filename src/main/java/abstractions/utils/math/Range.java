/*
 * 
 * This file is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * For a copy of the GNU Lesser General Public License see
 * <http://www.gnu.org/licenses/>.
 */

package abstractions.utils.math;

/**
 * A range is defined by a begin and an end. It allows checking whether a value
 * is within the range or outside. A range can be open at one or both ends, i.
 * e. the range is assumed to be endless in that direction.
 * 
 * @author Markus KARG (markus@headcrashing.eu)
 * @version 1.2.1
 * 
 */
public/*final*/class Range<T extends Comparable<T>> {

    private final T lowerBound;

    /*
     * Adaptation of original code: in order to preserve immutatibility,
     * getLowerBound() and getUpperBound() should return defensive copies if T
     * is not immutable. This is not done in this adaptation.
     */

    public final T getLowerBound() {
        return this.lowerBound;
    }

    public final T getUpperBound() {
        return this.upperBound;
    }

    //////////////////////////////////////////////////////

    private final T upperBound;

    /**
     * Creates a range with the specified bounds. The bounds will be included,
     * i. e. are part of the range. Bounds can be declared as being open, i. e.
     * the range is assumed to be endless in that direction.
     * 
     * @param lowerBound
     *            The lowest possible value of the range, or {@code null} if
     *            there is no lower bound.
     * @param upperBound
     *            The greatest possible value of the range, or {@code null} if
     *            there is no upper bound.
     * @throws IllegalArgumentException
     *             if lower bound is greater than upper bound
     */
    public Range(final T lowerBound, final T upperBound) throws IllegalArgumentException {
        if (lowerBound != null && upperBound != null && lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("lowerBound is greater than upperBound");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Checks whether the specified object is within the range, including
     * bounds.
     * 
     * @param object
     *            The object to be checked. Must not be {@code null}.
     * @return {@code false} if {@code object} is lower than the lower bound or
     *         greater than the upper bound; otherwise {@code true}.
     * @throws IllegalArgumentException
     *             if {@code object} is {@code null}.
     */
    public final boolean contains(final T object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (this.lowerBound != null && object.compareTo(this.lowerBound) < 0) {
            return false; // NOPMD
        }

        if (this.upperBound != null && object.compareTo(this.upperBound) > 0) {
            return false; // NOPMD
        }

        return true;
    }

    /**
     * Checks whether the specified range is entirely contained in the range,
     * including bounds.
     * 
     * @param range
     *            The range to be checked. Must not be {@code null}.
     * @return {@code false} if {@code range} has a lower bound lower than the
     *         lower bound of this or an upper bound greater than the upper
     *         bound of this (i. e. {@code other} overlaps or is completely
     *         outside); otherwise {@code true}.
     * @throws IllegalArgumentException
     *             if {@code other} is {@code null}.
     * @since 1.1.0
     */
    public final boolean contains(final Range<T> range) throws IllegalArgumentException {
        if (range == null) {
            throw new IllegalArgumentException("range is null");
        }

        if (this.lowerBound != null && (range.lowerBound == null || range.lowerBound.compareTo(this.lowerBound) < 0)) {
            return false; // NOPMD
        }

        if (this.upperBound != null && (range.upperBound == null || range.upperBound.compareTo(this.upperBound) > 0)) {
            return false; // NOPMD
        }

        return true;
    }

    /**
     * Checks whether the specified range overlaps this range (i. e. whether the
     * ranges intersect).
     * 
     * @param range
     *            The {@code range} to be checked. Must not be {@code null}.
     * @return {@code false} if {@code range} has an upper bound lower than the
     *         lower bound of this or a lower bound greater than the upper bound
     *         of this; otherwise {@code true}.
     * @throws IllegalArgumentException
     *             if {@code range} is {@code null}.
     * @since 1.2.0
     */
    public final boolean overlaps(final Range<T> range) throws IllegalArgumentException {
        if (range == null) {
            throw new IllegalArgumentException("range is null");
        }

        if (this.upperBound != null && range.lowerBound != null && this.upperBound.compareTo(range.lowerBound) < 0) {
            return false; // NOPMD 
        }

        if (this.lowerBound != null && range.upperBound != null && this.lowerBound.compareTo(range.upperBound) > 0) {
            return false; // NOPMD
        }

        return true;
    }

    @Override
    public final int hashCode() {
        // MAX_value is used for open LOWER bound as it will be most far away
        // from any existing LOWER bound.
        final int lowerBoundHashCode = this.lowerBound == null ? Integer.MAX_VALUE : this.lowerBound.hashCode();

        // MIN_value is used for open UPPER bound as it will be most far away
        // from any existing UPPER bound.
        final int upperBoundHashCode = this.upperBound == null ? Integer.MIN_VALUE : this.upperBound.hashCode();

        return lowerBoundHashCode << 16 ^ upperBoundHashCode;
    }

    @Override
    public final boolean equals(final Object other) {
        if (!(other instanceof Range)) {
            return false; // NOPMD
        }

        final Range<?> that = (Range<?>) other;

        return Range.equals(this.lowerBound, that.lowerBound) && Range.equals(this.upperBound, that.upperBound);
    }

    protected/*private*/static final boolean equals(final Object a, final Object b) {
        if (a == null && b == null) {
            return true; // NOPMD 
        }

        if (a != null && b != null) {
            return a.equals(b); // NOPMD
        }

        return false;
    }

    @Override
    public final String toString() {
        return String.format("Range[%s, %s]", this.lowerBound, this.upperBound);
    }

}