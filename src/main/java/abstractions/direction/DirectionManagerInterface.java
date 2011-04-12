
package abstractions.direction;

import java.util.List;

public interface DirectionManagerInterface {

    DirectionInterface getDirection(final NamedDirection label);

    DirectionInterface reduce(final List<DirectionInterface> directions);

}
