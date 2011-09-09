
package abstractions.immutable.pieces.types;

import java.lang.reflect.InvocationTargetException;

import abstractions.immutable.ImmutableInterface;

public final class PieceType implements PieceTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static class Null implements ImmutableInterface<Null> {

        private final static Null INSTANCE = new Null();

        public static Null from() {
            return INSTANCE;
        }

        private Null() {}

        @Override
        public Null apply() {
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    public final static PieceTypeInterface NULL = new PieceType(newType(Null.class));

    /*-------------------------------------8<-------------------------------------*/

    private final ImmutableInterface<?> type;

    @Override
    public ImmutableInterface<?> type() {
        return this.type;
    }

    /*-------------------------------------8<-------------------------------------*/

    private static ImmutableInterface<?> newType(final Class<? extends ImmutableInterface<?>> typeClass) {

        ImmutableInterface<?> instance = null;

        try {
            instance = (ImmutableInterface<?>) typeClass.getMethod("from").invoke(null, (Object[]) null);
        }
        catch (final SecurityException e) {
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (final InvocationTargetException e) {
            e.printStackTrace();
        }

        return instance;
    }

    /*-------------------------------------8<-------------------------------------*/

    // TODO ?? masquer ou ne pas masquer les NPE
    public static PieceTypeInterface from(final ImmutableInterface<?> type) {
        return type == null ? NULL : new PieceType(type);
    }

    // TODO ?? masquer ou ne pas masquer les NPE
    public static PieceTypeInterface from(final Class<? extends ImmutableInterface<?>> typeClass) {
        return typeClass == null ? NULL : from(newType(typeClass));
    }

    private PieceType(final ImmutableInterface<?> type) {
        this.type = type;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceTypeInterface apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.type() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        final PieceTypeInterface pt1 = PieceType.from(Null.class);
        final PieceTypeInterface pt2 = PieceType.from(Null.from());

        final ImmutableInterface<Object> type = null;
        final PieceTypeInterface pt3 = PieceType.from(type);
        final Class<ImmutableInterface<Object>> typeClass = null;
        final PieceTypeInterface pt4 = PieceType.from(typeClass);

        final PieceTypeInterface pt5 = PieceType.from(Pawn.class);
        final PieceTypeInterface pt6 = PieceType.from(Pawn.from());

        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println(pt3);
        System.out.println(pt4);
        System.out.println(pt5);
        System.out.println(pt6);

    }
    /*-------------------------------------8<-------------------------------------*/

}