
package abstractions.immutable.positions;

import java.util.Map;

import abstractions.immutable.directions.DirectionInterface;

import com.google.common.collect.Maps;

public final class Position implements PositionInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final int row, final int column) {
        return (17 * 31 + row) * 31 + column;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class PositionFactory {

        private final static Map<Integer, PositionInterface> CACHE = Maps.newHashMap();

        public static PositionInterface get(final int row, final int column) {
            PositionInterface position;
            final int address = computeHashCode(row, column);
            if ((position = CACHE.get(address)) == null) {
                position = new Position(row, column);
                CACHE.put(address, position);
            }
            return position;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int row;

    @Override
    public int row() {
        return this.row;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int column;

    @Override
    public int column() {
        return this.column;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PositionInterface from(final int row, final int column) {
        return PositionFactory.get(row, column);
    }

    private Position(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.hashCode = computeHashCode(row, column);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PositionInterface apply() {
        return this;
    }

    @Override
    public PositionInterface apply(final int row, final int column) {
        return row == this.row() && column == this.column() ? this.apply() : from(row, column);
    }

    @Override
    public PositionInterface apply(final DirectionInterface direction) {
        return direction == null ? this.apply() : this.apply(this.row() + direction.rowDelta(), this.column() + direction.columnDelta());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof PositionInterface))
            return false;
        final PositionInterface that = (PositionInterface) object;
        if (that.hashCode() != this.hashCode())
            return false;
        return that.row() == this.row() && that.column() == this.column();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "( " + this.row() + " , " + this.column() + " ) ";
    }

    /*-------------------------------------8<-------------------------------------*/

}