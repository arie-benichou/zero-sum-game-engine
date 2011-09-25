
package fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position;

import java.util.Map;

import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.direction.DirectionInterface;

public final class Position implements PositionInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static PositionInterface NULL = new Position(0, 0);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final int row, final int column) {
        //return (17 * 31 + row) * 31 + column;
        return (row + "|" + column).hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, PositionInterface> CACHE = Maps.newHashMap();

        public static PositionInterface get(int row, int column) {
            if (row < 1) row = 0;
            if (column < 1) column = 0;
            if (row == 0 || column == 0) return NULL;
            final int address = computeHashCode(row, column);
            PositionInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new Position(row, column);
                CACHE.put(address, instance);
            }
            else
                ++cacheHits;
            return instance;
        }

        public final static int size() {
            return CACHE.size();
        }

        public final static int cacheHits() {
            return cacheHits;
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
        return NULL.apply(row, column);
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
        return row == this.row() && column == this.column() ? this.apply() : Factory.get(row, column);
    }

    @Override
    public PositionInterface apply(final DirectionInterface direction) {
        return direction == null ? this.apply() : this.apply(this.row() + direction.rowDelta(), this.column() + direction.columnDelta());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof PositionInterface)) return false;
        final PositionInterface that = (PositionInterface) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.row() == this.row() && that.column() == this.column();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public int compareTo(final PositionInterface that) {
        if (this.row() < that.row()) return -1;
        if (this.row() > that.row()) return 1;
        if (this.column() < that.column()) return -1;
        if (this.column() > that.column()) return 1;
        return 0;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.row() + ", " + this.column() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        System.out.println(Position.from(0, 0));
        System.out.println(Position.from(0, 1));
        System.out.println(Position.from(1, 1));
        System.out.println(Position.from(-1, -1));
        System.out.println(Position.from(-1, 10));
        System.out.println(Position.from(-1, -10));
        System.out.println(Position.from(1, 10));
        System.out.println(Position.from(1, 10).apply(10, 1));

        System.out.println(Factory.CACHE.size());

    }

}