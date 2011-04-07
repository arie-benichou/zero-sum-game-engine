
package abstractions.dimension;

import abstractions.utils.math.IntegersRange;

final class ColumnsRange extends IntegersRange {

    public ColumnsRange(final Integer lowerBound, final Integer upperBound) throws IllegalArgumentException {
        super(lowerBound, upperBound);
    }
}