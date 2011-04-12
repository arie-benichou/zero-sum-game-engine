
package abstractions.direction;

import java.util.Collection;
import java.util.Map;

public interface DirectionManagerInterface {

    DirectionInterface getNamedDirection(final NamedDirection label);

    DirectionInterface getDirection(final int rowDelta, final int columnDelta);

    DirectionInterface reduce(final Collection<DirectionInterface> directions);

    Map<NamedDirection, DirectionInterface> getDirectionsMap();

}
