
package abstractions.immutable.side;

import java.util.Map;

import com.google.common.collect.Maps;

public final class Side implements SideInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static SideInterface NULL = new Side(0);

    /*-------------------------------------8<-------------------------------------*/

    public final static class SideFactory {

        private final static Map<Integer, SideInterface> CACHE = Maps.newHashMap();

        public static SideInterface get(final int value) {
            SideInterface instance;
            if ((instance = CACHE.get(value)) == null) {
                instance = new Side(value);
                CACHE.put(value, instance);
            }
            return instance;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int value;

    @Override
    public int value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static SideInterface from(final int value) {
        return value == 0 ? NULL : SideFactory.get(value);
    }

    // TODO ?? masquer ou ne pas masquer les NPE
    public static SideInterface from(final Integer value) {
        return value == null ? NULL : SideFactory.get(value);
    }

    private Side(final int value) {
        this.value = value;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public SideInterface apply() {
        return this;
    }

    @Override
    public SideInterface apply(final int value) {
        return value == this.value() ? this.apply() : from(value);
    }

    @Override
    public SideInterface opposite() {
        return this.apply(-this.value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean is(final int value) {
        return this.value == value;
    }

    @Override
    public boolean isNull() {
        return this.is(0);
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

        i = null;
        System.out.println(Side.from(i));

    }

}
