
package abstractions.direction;

import java.util.List;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public class DirectionManager implements DirectionManagerInterface {

    private final static DirectionInterface NULL_DIRECTION = new Direction(0, 0);

    private final DimensionInterface dimension;
    private final int hashFactor;
    private final int hashOffset;
    private final List<DirectionInterface> data = null;

    private DirectionInterface newDirection(final int rowDelta, final int columnDelta) {
        return new Direction(rowDelta, columnDelta);
    }

    private int computeHashFactor(final DimensionInterface dimension) {
        return (dimension.numberOfColumns() - 1) * 2 + 1;
    }

    private int computeHashOffset(final DimensionInterface dimension) {
        //((dimension.numberOfRows() - 1) + (dimension.numberOfRows() - 0)) * ((dimension.numberOfColumns() - 1) + (dimension.numberOfColumns() - 0))
        final int n = (2 * dimension.numberOfRows() - 1) * (2 * dimension.numberOfColumns() - 1);
        return n / 2;
    }

    private int computeNaturalHash(final int rowDelta, final int columnDelta) {
        return this.hashOffset + this.hashFactor * rowDelta + columnDelta;

    }

    private void manage(final DirectionInterface direction) {
        //this.data.add(this.computeNaturalHash(direction.getRowDelta(), direction.getColumnDelta()), direction);

        final int index = this.computeNaturalHash(direction.getRowDelta(), direction.getColumnDelta());

        System.out.println(index);

        this.data.set(index, direction);
    }

    public DirectionManager(final DimensionInterface dimension) throws Exception {

        this.dimension = dimension;

        this.hashFactor = this.computeHashFactor(this.dimension);
        System.out.println("hashFactor: " + this.hashFactor);

        this.hashOffset = this.computeHashOffset(this.dimension);
        System.out.println("size of set: " + this.hashOffset * 2);

        System.out.println("hashOffset: " + this.hashOffset);

        System.out.println("\n");

        /*
        this.data = Lists.newArrayListWithCapacity(2 * this.hashOffset + 1);
        Collections.fill(this.data, DirectionManager.NULL_DIRECTION);
        */

        final int maxRowDelta = this.dimension.numberOfRows() - 1;
        final int maxColumnDelta = this.dimension.numberOfColumns() - 1;

        int n = 0;
        for (int rowDelta = -maxRowDelta; rowDelta <= maxRowDelta; ++rowDelta) {
            for (int columnDelta = -maxColumnDelta; columnDelta <= maxColumnDelta; ++columnDelta) {
                final int hash = this.computeNaturalHash(rowDelta, columnDelta);
                System.out.println(rowDelta + " | " + columnDelta);
                System.out.println(n);
                System.out.println("natural hash : " + hash);
                if (n != hash) {
                    throw new Exception("fuck");
                }
                System.out.println("\n");
                ++n;
            }
        }

    }

    public DirectionInterface getDirection(final NamedDirection label) {
        return this.data.get(this.computeNaturalHash(label.getRowDelta(), label.getColumnDelta()));
    }

    public DirectionInterface reduce(final List<DirectionInterface> directions) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(final String[] args) throws Exception {
        new DirectionManager(DimensionFactory.Dimension(4, 5));
        new DirectionManager(DimensionFactory.Dimension(3, 3));
        new DirectionManager(DimensionFactory.Dimension(5, 4));
    }
}
