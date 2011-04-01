
package utils.math;


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