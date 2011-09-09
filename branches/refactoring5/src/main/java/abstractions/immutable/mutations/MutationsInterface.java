
package abstractions.immutable.mutations;

import java.util.Map;

public interface MutationsInterface<ID, VALUE> {

    Map<ID, VALUE> mutations();

}