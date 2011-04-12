
package abstractions.direction;

import java.util.List;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public class DirectionManager implements DirectionManagerInterface {

    private final DimensionInterface dimension;
    private final int hashFactor;
    private final int hashOffset;

    public DirectionManager(final DimensionInterface dimension) {
        this.dimension = dimension;
        this.hashFactor = this.computeHashFactor(this.dimension);
        this.hashOffset = this.computeHashOffset(this.dimension);

        // TODO à stocker dans une ArrayList de taille 2 * hashOffset + 1        
        for (final NamedDirection namedDirection : NamedDirection.values()) {
            new Direction(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
        }

    }

    private int computeHashFactor(final DimensionInterface dimension) {
        //Maximum des deltas de chaque dimension multiplié par le nombre de dimensions plus le delta nul.
        return (Math.max(dimension.numberOfRows(), dimension.numberOfColumns()) - 1) * 2 + 1;
    }

    private int computeHashOffset(final DimensionInterface dimension) {
        return 2 * dimension.numberOfColumns() + 2 * dimension.numberOfRows();
    }

    private int computeNaturalHash(final int rowDelta, final int columnDelta) {
        return this.hashFactor * rowDelta + columnDelta + this.hashOffset;
    }

    public DirectionInterface getDirection(final NamedDirection directionLabel) {
        // TODO Auto-generated method stub
        return null;
    }

    public DirectionInterface reduce(final List<DirectionInterface> directions) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(final String[] args) {
        final DimensionInterface dim = DimensionFactory.Dimension(3, 3);
        final DirectionManager dm = new DirectionManager(dim);

        System.out.println(dm.computeHashFactor(dim));
        System.out.println(dm.computeNaturalHash(-2, -2));
        System.out.println(dm.computeNaturalHash(0, 0));
        System.out.println(dm.computeNaturalHash(2, 2));

    }
}
