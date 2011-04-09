
package abstractions.position;

import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

interface PositionSetFactoryInterface {

    /**
     * Returns an unmodifiable set of new positions for a given dimension.
     * 
     * @param dimension
     *            a given dimension
     * 
     * @return an unmodifiable set of new positions for a given dimension.
     */
    public Set<PositionInterface> newPositionSet(final DimensionInterface dimension);

}
