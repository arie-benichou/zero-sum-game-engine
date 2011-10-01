
package context.entity.game.board.cell.piece.side;

import java.util.Map;

import com.google.common.collect.Maps;

public final class Side implements SideInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static SideInterface NULL = new Side(0);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final int value) {
        return (17 * 31 + value) * 31;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class Factory {

        private static int cacheHits;

        private final static Map<Integer, SideInterface> CACHE = Maps.newHashMap();

        public static SideInterface get(final int value) {
            if (value == 0) return NULL;
            SideInterface instance = CACHE.get(value);
            if (instance == null) {
                instance = new Side(value);
                CACHE.put(value, instance);
            }
            else ++cacheHits;
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

    private final int value;

    @Override
    public int value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static SideInterface from() {
        return NULL;
    }

    public static SideInterface from(final int value) {
        return NULL.apply(value);
    }

    public static SideInterface from(final Integer value) {
        return value == null ? NULL : NULL.apply(value);
    }

    private Side(final int value) {
        this.value = value;
        this.hashCode = computeHashCode(value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public SideInterface apply() {
        return this;
    }

    @Override
    public SideInterface apply(final int value) {
        return value == this.value() ? this.apply() : Factory.get(value);
    }

    @Override
    public SideInterface apply(final Integer value) {
        return value == null || value == this.value() ? this.apply() : Factory.get(value);
    }

    @Override
    public SideInterface opposite() {
        return this.apply(-this.value());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isNull() {
        return this.equals(NULL);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof SideInterface))
            return false;
        return ((SideInterface) object).value() == this.value();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        System.out.println(Side.NULL);
        System.out.println(Side.from(0));

        System.out.println(Side.from(1));

        Integer i;
        i = 0;
        System.out.println(Side.from(i));
        i = 1;
        System.out.println(Side.from(i));

        System.out.println(Side.from(null));

        System.out.println(Factory.CACHE.size());

        System.out.println(Side.from(1).hashCode());
        System.out.println(Side.from(1).hashCode());

    }

}
