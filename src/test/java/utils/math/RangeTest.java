/*
 * Copyright 2010 Markus KARG
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

package utils.math;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import abstractions.utils.math.Range;

/**
 * Unit test for {@code Range} class.
 * 
 * @author Markus KARG (markus@headcrashing.eu)
 */
public final class RangeTest {

    @Test(expected = IllegalArgumentException.class)
    public final void constructorWithInterchangedBounds() {
        new Range<Integer>(2, 1);
    }

    @Test
    public final void _equals() {
        Assert.assertThat("(null, null).equals(123)", new Range<Integer>(null, null).equals(123), Is.is(false));
        Assert.assertThat("(null, null).equals(null, null)", new Range<Integer>(null, null).equals(new Range<Integer>(null, null)), Is.is(true));
        Assert.assertThat("(1, 2).equals(1, 2)", new Range<Integer>(1, 2).equals(new Range<Integer>(1, 2)), Is.is(true));
        Assert.assertThat("(null, 2).equals(null, 2)", new Range<Integer>(null, 2).equals(new Range<Integer>(null, 2)), Is.is(true));
        Assert.assertThat("(1, null).equals(1, null)", new Range<Integer>(1, null).equals(new Range<Integer>(1, null)), Is.is(true));
        Assert.assertThat("(1, 2).equals(1, 3)", new Range<Integer>(1, 2).equals(new Range<Integer>(1, 3)), Is.is(false));
        Assert.assertThat("(1, 2).equals(0, 2)", new Range<Integer>(1, 2).equals(new Range<Integer>(0, 2)), Is.is(false));
        Assert.assertThat("(1, 2).equals(null, 2)", new Range<Integer>(1, 2).equals(new Range<Integer>(null, 2)), Is.is(false));
        Assert.assertThat("(1, 2).equals(1, null)", new Range<Integer>(1, 2).equals(new Range<Integer>(1, null)), Is.is(false));
        Assert.assertThat("(1, 2).equals(null, null)", new Range<Integer>(1, 2).equals(new Range<Integer>(null, null)), Is.is(false));
        Assert.assertThat("(null, 2).equals(1, 2)", new Range<Integer>(null, 2).equals(new Range<Integer>(1, 2)), Is.is(false));
        Assert.assertThat("(1, null).equals(1, 2)", new Range<Integer>(1, null).equals(new Range<Integer>(1, 2)), Is.is(false));
        Assert.assertThat("(null, null).equals(1, 2)", new Range<Integer>(null, null).equals(new Range<Integer>(1, 2)), Is.is(false));
    }

    @Test
    public final void _toString() {
        Assert.assertThat("(A, B).toString()", new Range<String>("A", "B").toString(), Is.is("Range[A, B]"));
    }

    @Test
    public final void contains() {
        Assert.assertThat("(1, 3).contains(0)", new Range<Integer>(1, 3).contains(0), Is.is(false));
        Assert.assertThat("(1, 3).contains(1)", new Range<Integer>(1, 3).contains(1), Is.is(true));
        Assert.assertThat("(1, 3).contains(2)", new Range<Integer>(1, 3).contains(2), Is.is(true));
        Assert.assertThat("(1, 3).contains(3)", new Range<Integer>(1, 3).contains(3), Is.is(true));
        Assert.assertThat("(1, 3).contains(4)", new Range<Integer>(1, 3).contains(4), Is.is(false));
        Assert.assertThat("(null, 3).contains(2)", new Range<Integer>(null, 3).contains(2), Is.is(true));
        Assert.assertThat("(1, null).contains(2)", new Range<Integer>(1, null).contains(2), Is.is(true));
        Assert.assertThat("(null, null).contains(2)", new Range<Integer>(null, null).contains(2), Is.is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void containsNull() {
        new Range<Integer>(1, 3).contains((Integer) null);
    }

    @Test
    public final void containsRange() {
        final Range<Integer> boundedRange = new Range<Integer>(0, 10);
        Assert.assertThat("Bounded range contains smaller bounded range", boundedRange.contains(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Bounded range contains equal bounded range", boundedRange.contains(new Range<Integer>(0, 10)), Is.is(true));
        Assert.assertThat("Bounded range contains smaller bounded range aligned left", boundedRange.contains(new Range<Integer>(0, 8)), Is.is(true));
        Assert.assertThat("Bounded range contains smaller bounded range aligned right", boundedRange.contains(new Range<Integer>(2, 10)), Is.is(true));
        Assert.assertThat("Bounded range contains larger bounded range", boundedRange.contains(new Range<Integer>(5, 11)), Is.is(false));
        Assert.assertThat("Bounded range contains smaller bounded range overlapping left", boundedRange.contains(new Range<Integer>(-1, 4)), Is.is(false));
        Assert.assertThat("Bounded range contains left open range located within", boundedRange.contains(new Range<Integer>(null, 4)), Is.is(false));
        Assert.assertThat("Bounded range contains left open range located outside", boundedRange.contains(new Range<Integer>(null, 11)), Is.is(false));
        Assert.assertThat("Bounded range contains right open range located within", boundedRange.contains(new Range<Integer>(2, null)), Is.is(false));
        Assert.assertThat("Bounded range contains right open range located outside", boundedRange.contains(new Range<Integer>(-1, null)), Is.is(false));
        Assert.assertThat("Bounded range contains unbounded range", boundedRange.contains(new Range<Integer>(null, null)), Is.is(false));

        final Range<Integer> leftOpenRange = new Range<Integer>(null, 10);
        Assert.assertThat("Left open range contains smaller bounded range", leftOpenRange.contains(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Left open range contains left open range located within", leftOpenRange.contains(new Range<Integer>(null, 3)), Is.is(true));
        Assert.assertThat("Left open range contains left open range located outside", leftOpenRange.contains(new Range<Integer>(null, 11)), Is.is(false));
        Assert.assertThat("Left open range contains right overlapping range", leftOpenRange.contains(new Range<Integer>(2, 11)), Is.is(false));
        Assert.assertThat("Left open range contains right open range located inside", leftOpenRange.contains(new Range<Integer>(2, null)), Is.is(false));
        Assert.assertThat("Left open range contains unbounded range", leftOpenRange.contains(new Range<Integer>(null, null)), Is.is(false));

        final Range<Integer> rightOpenRange = new Range<Integer>(0, null);
        Assert.assertThat("Right open range contains smaller bounded ranged", rightOpenRange.contains(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Right open range contains right open range located within", rightOpenRange.contains(new Range<Integer>(2, null)), Is.is(true));
        Assert.assertThat("Right open range contains right open range located outside", rightOpenRange.contains(new Range<Integer>(-1, null)), Is.is(false));
        Assert.assertThat("Right open range contains smaller bounded range overlapping left", rightOpenRange.contains(new Range<Integer>(-1, 8)), Is.is(false));
        Assert.assertThat("Right open range contains left open range located inside", rightOpenRange.contains(new Range<Integer>(null, 8)), Is.is(false));
        Assert.assertThat("Right open range contains unbounded range", rightOpenRange.contains(new Range<Integer>(null, null)), Is.is(false));

        final Range<Integer> unbounded = new Range<Integer>(null, null);
        Assert.assertThat("Unbounded range contains bounded range", unbounded.contains(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Unbounded range contains right open range", unbounded.contains(new Range<Integer>(2, null)), Is.is(true));
        Assert.assertThat("Unbounded range contains left open range", unbounded.contains(new Range<Integer>(null, 3)), Is.is(true));
        Assert.assertThat("Unbounded range contains unbounded range", unbounded.contains(new Range<Integer>(null, null)), Is.is(true));
    }

    @Test
    public final void overlaps() {
        final Range<Integer> boundedRange = new Range<Integer>(0, 10);
        Assert.assertThat("Bounded range overlaps smaller bounded range", boundedRange.overlaps(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Bounded range overlaps smaller bounded range overlapping right", boundedRange.overlaps(new Range<Integer>(9, 11)), Is.is(true));
        Assert.assertThat("Bounded range overlaps smaller bounded range overlapping left", boundedRange.overlaps(new Range<Integer>(-1, 1)), Is.is(true));
        Assert.assertThat("Bounded range overlaps left open range located within", boundedRange.overlaps(new Range<Integer>(null, 5)), Is.is(true));
        Assert.assertThat("Bounded range overlaps left open range located outside", boundedRange.overlaps(new Range<Integer>(null, -4)), Is.is(false));
        Assert.assertThat("Bounded range overlaps right open range located within", boundedRange.overlaps(new Range<Integer>(5, null)), Is.is(true));
        Assert.assertThat("Bounded range overlaps right open range located outside", boundedRange.overlaps(new Range<Integer>(12, null)), Is.is(false));
        Assert.assertThat("Bounded range overlaps unbounded range", boundedRange.overlaps(new Range<Integer>(null, null)), Is.is(true));
        Assert.assertThat("Bounded range overlaps bounded range located outside left", boundedRange.overlaps(new Range<Integer>(-5, -1)), Is.is(false));
        Assert.assertThat("Bounded range overlaps bounded range located outside right", boundedRange.overlaps(new Range<Integer>(11, 14)), Is.is(false));

        final Range<Integer> leftOpenRange = new Range<Integer>(null, 10);
        Assert.assertThat("Left open range overlaps bounded range located within", leftOpenRange.overlaps(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Left open range overlaps bounded range located outside", leftOpenRange.overlaps(new Range<Integer>(11, 15)), Is.is(false));
        Assert.assertThat("Left open range overlaps bounded range overlapping right", leftOpenRange.overlaps(new Range<Integer>(9, 11)), Is.is(true));
        Assert.assertThat("Left open range overlaps left open range located outside right", leftOpenRange.overlaps(new Range<Integer>(null, 11)), Is.is(true));
        Assert.assertThat("Left open range overlaps left open range located within", leftOpenRange.overlaps(new Range<Integer>(null, 3)), Is.is(true));
        Assert.assertThat("Left open range overlaps right open range located outside right", leftOpenRange.overlaps(new Range<Integer>(11, null)), Is.is(false));
        Assert.assertThat("Left open range overlaps unbound range", leftOpenRange.overlaps(new Range<Integer>(null, null)), Is.is(true));

        final Range<Integer> rightOpenRange = new Range<Integer>(0, null);
        Assert.assertThat("Right open range overlaps bounded range located within", rightOpenRange.overlaps(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Right open range overlaps bounded range located outside", rightOpenRange.overlaps(new Range<Integer>(-4, -1)), Is.is(false));
        Assert.assertThat("Right open range overlaps bounded range overlapping left", rightOpenRange.overlaps(new Range<Integer>(-1, 8)), Is.is(true));
        Assert.assertThat("Right open range overlaps left open range located outside left", rightOpenRange.overlaps(new Range<Integer>(null, -1)), Is.is(false));
        Assert.assertThat("Right open range overlaps right open range within", rightOpenRange.overlaps(new Range<Integer>(2, null)), Is.is(true));
        Assert.assertThat("Right open range overlaps right open range located outside left", rightOpenRange.overlaps(new Range<Integer>(-1, null)), Is.is(true));
        Assert.assertThat("Right open range overlaps unbound range", rightOpenRange.overlaps(new Range<Integer>(null, null)), Is.is(true));

        final Range<Integer> unboundedRange = new Range<Integer>(null, null);
        Assert.assertThat("Unbounded range overlaps bounded range", unboundedRange.overlaps(new Range<Integer>(2, 3)), Is.is(true));
        Assert.assertThat("Unbounded range overlaps left open range", unboundedRange.overlaps(new Range<Integer>(null, 3)), Is.is(true));
        Assert.assertThat("Unbounded range overlaps right open range", unboundedRange.overlaps(new Range<Integer>(2, null)), Is.is(true));
        Assert.assertThat("Unbounded range overlaps unbounded range", unboundedRange.overlaps(new Range<Integer>(null, null)), Is.is(true));
    }

    @Test
    public final void _hashCode() {
        final Integer hashCodeOfMaxValue = Integer.valueOf(Integer.MAX_VALUE).hashCode();
        final Integer hashCodeOfMinValue = Integer.valueOf(Integer.MIN_VALUE).hashCode();
        final Integer hashCodeOf7 = Integer.valueOf(7).hashCode();
        final Integer hashCodeOf11 = Integer.valueOf(11).hashCode();
        Assert.assertThat("(null, null).hashCode()", new Range<Integer>(null, null).hashCode(), Is.is(hashCodeOfMaxValue << 16 ^ hashCodeOfMinValue));
        Assert.assertThat("(7, null).hashCode()", new Range<Integer>(7, null).hashCode(), Is.is(hashCodeOf7 << 16 ^ hashCodeOfMinValue));
        Assert.assertThat("(null, 11).hashCode()", new Range<Integer>(null, 11).hashCode(), Is.is(hashCodeOfMaxValue << 16 ^ hashCodeOf11));
        Assert.assertThat("(7, 11).hashCode()", new Range<Integer>(7, 11).hashCode(), Is.is(hashCodeOf7 << 16 ^ hashCodeOf11));
    }

    /**
     * Regression test for {@code Range<String>.compare(String)} false
     * positives.
     * 
     * Problem: {@code Range<String>("c", "f")} pretends to contain both,
     * {@code "a"} and {@code "g"}.
     * 
     * Cause: Incorrect assumption of {@code Comparable<T>.compareTo(T)} result
     * values being exactly {@code -1} or {@code 1}, but actually can be
     * {@code -N} and {@code N}.
     */
    @Test
    public final void compareStringRegression() {
        Assert.assertThat("(\"c\", \"e\").contains(\"a\")", new Range<String>("c", "e").contains("a"), Is.is(false));
        Assert.assertThat("(\"c\", \"e\").contains(\"g\")", new Range<String>("c", "e").contains("g"), Is.is(false));
    }

    /**
     * Regression test for {@code Range<String>(String, String)} false negatives
     * in interchanged limits test.
     * 
     * Problem: {@code Range<String>("c", "a")} pretends to be a valid range.
     * 
     * Cause: Incorrect assumption of {@code Comparable<T>.compareTo(T)} result
     * values being exactly {@code -1} or {@code 1}, but actually can be
     * {@code -N} and {@code N}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void interchangedLimitsStringRegression() {
        new Range<String>("c", "a");
    }
}