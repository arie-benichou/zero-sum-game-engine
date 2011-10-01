
package fr.designpattern.zerosumgames.abstractions.move.mutation;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;

import com.google.common.collect.ImmutableSortedMap;

public interface MutationInterface<ADDRESS, VALUE> extends ImmutableInterface<MutationInterface<ADDRESS, VALUE>> {

    ImmutableSortedMap<ADDRESS, VALUE> value();

}