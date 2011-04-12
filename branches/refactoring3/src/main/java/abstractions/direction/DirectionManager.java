
package abstractions.direction;

import java.util.ArrayList;
import java.util.List;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public class DirectionManager implements DirectionManagerInterface {

    private final static DirectionInterface NULL_DIRECTION = new Direction(0, 0);

    private final DimensionInterface dimension;
    private final int hashFactor;
    private final int hashOffset;
    private final List<DirectionInterface> data;

    private DirectionInterface newDirection(final int rowDelta, final int columnDelta) {
        return new Direction(rowDelta, columnDelta);
    }

    private int computeHashFactor(final DimensionInterface dimension) {
        return (dimension.numberOfColumns() - 1) * 2 + 1;
    }

    private int computeHashOffset(final DimensionInterface dimension) {
        //((dimension.numberOfRows() - 1) + (dimension.numberOfRows() - 0)) * ((dimension.numberOfColumns() - 1) + (dimension.numberOfColumns() - 0)) / 2
        return (2 * dimension.numberOfRows() - 1) * (2 * dimension.numberOfColumns() - 1) / 2;
    }

    private int computeNaturalHash(final int rowDelta, final int columnDelta) {
        return this.hashOffset + this.hashFactor * rowDelta + columnDelta;
    }

    private List<DirectionInterface> initializeData(final List<DirectionInterface> data) {
        final int maxRowDelta = this.dimension.numberOfRows() - 1;
        final int maxColumnDelta = this.dimension.numberOfColumns() - 1;
        for (int checkSum = 0, rowDelta = -maxRowDelta; rowDelta <= maxRowDelta; ++rowDelta) {
            for (int columnDelta = -maxColumnDelta; columnDelta <= maxColumnDelta; ++columnDelta) {
                final DirectionInterface managedDirection = this.newDirection(rowDelta, columnDelta);
                final int hash = this.computeNaturalHash(managedDirection.getRowDelta(), managedDirection.getColumnDelta());
                if (hash != checkSum++) {
                    throw new RuntimeException("Error in hash function.");
                }
                data.add(hash, managedDirection);
            }
        }
        return data;
    }

    public DirectionManager(final DimensionInterface dimension) {
        this.dimension = dimension;
        this.hashFactor = this.computeHashFactor(this.dimension);
        this.hashOffset = this.computeHashOffset(this.dimension);
        this.data = this.initializeData(new ArrayList<DirectionInterface>(2 * this.hashOffset + 1));
    }

    public DirectionInterface getDirection(final NamedDirection label) {
        return this.data.get(this.computeNaturalHash(label.getRowDelta(), label.getColumnDelta()));
    }

    public DirectionInterface reduce(final List<DirectionInterface> directions) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(final String[] args) {

        new DirectionManager(DimensionFactory.Dimension(4, 5));
        new DirectionManager(DimensionFactory.Dimension(3, 3));
        final DirectionManagerInterface manager = new DirectionManager(DimensionFactory.Dimension(5, 4));

        System.out.println(manager.getDirection(NamedDirection.TOP));
        System.out.println(manager.getDirection(NamedDirection.TOP_RIGHT));
        System.out.println(manager.getDirection(NamedDirection.RIGHT));
        System.out.println(manager.getDirection(NamedDirection.BOTTOM_RIGHT));
        System.out.println(manager.getDirection(NamedDirection.BOTTOM));
        System.out.println(manager.getDirection(NamedDirection.BOTTOM_LEFT));
        System.out.println(manager.getDirection(NamedDirection.LEFT));
        System.out.println(manager.getDirection(NamedDirection.TOP_LEFT));

    }
}
