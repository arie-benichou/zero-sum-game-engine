
package context.entity.game.board.mutation;

import util.interfaces.ImmutableInterface;
import util.interfaces.NullObjectInterface;

import com.google.common.collect.ImmutableSortedMap;

public interface MutationInterface<ADDRESS, VALUE> extends ImmutableInterface<MutationInterface<ADDRESS, VALUE>>, NullObjectInterface {

    ImmutableSortedMap<ADDRESS, VALUE> value();

}