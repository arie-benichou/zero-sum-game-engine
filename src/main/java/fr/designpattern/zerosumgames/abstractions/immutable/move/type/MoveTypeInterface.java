
package fr.designpattern.zerosumgames.abstractions.immutable.move.type;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.ConcreteMoveTypeInterface;

// TODO ?? créer une TypeFactoryInterface générique et utiliser directement les
// types construits par la factory au lieu de devoir appeler value()
public interface MoveTypeInterface extends ImmutableInterface<MoveTypeInterface> { // TODO créer une TypeInterface de plus haut niveau

    /*-------------------------------------8<-------------------------------------*/

    @Override
    MoveTypeInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    ConcreteMoveTypeInterface value();

    MoveTypeInterface apply(ConcreteMoveTypeInterface value);

    MoveTypeInterface apply(Class<? extends ConcreteMoveTypeInterface> valueClass);

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