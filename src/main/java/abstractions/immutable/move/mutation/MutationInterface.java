
package abstractions.immutable.move.mutation;

import abstractions.immutable.ImmutableInterface;

import com.google.common.collect.ImmutableSortedMap;

public interface MutationInterface<ADDRESS, VALUE> extends ImmutableInterface<MutationInterface<ADDRESS, VALUE>> {

    ImmutableSortedMap<ADDRESS, VALUE> value();

}