
package abstractions.immutable.move.type;

import abstractions.immutable.ImmutableInterface;

// TODO créer une TypeFactoryInterface générique et utiliser directement les types
// construits par la factory au lieu de devoir appeler value()
public interface MoveTypeInterface extends ImmutableInterface<MoveTypeInterface> { // TODO créer une TypeInterface de plus haut niveau

    /*-------------------------------------8<-------------------------------------*/

    @Override
    MoveTypeInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    ImmutableInterface<?> value();

    MoveTypeInterface apply(ImmutableInterface<?> value);

    MoveTypeInterface apply(Class<? extends ImmutableInterface<?>> valueClass);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    String toString();

    /*-------------------------------------8<-------------------------------------*/

}
