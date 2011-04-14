/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.direction;

import java.util.Collection;
import java.util.List;

import abstractions.dimension.API.DimensionInterface;
import abstractions.direction.DirectionManager.NamedDirection;

public interface DirectionManagerInterface {

    /**
     * Returns a named direction.
     * 
     * @param name
     *            the name of the direction
     * 
     * @return a named direction
     */
    DirectionInterface getNamedDirection(final NamedDirection name);

    /**
     * Returns a direction.
     * 
     * @param rowDelta
     *            row delta
     * @param columnDelta
     *            column delta
     * 
     * @return a direction
     */
    DirectionInterface getDirection(final int rowDelta, final int columnDelta);

    /**
     * Reduces a collection of directions.
     * 
     * @param directions
     *            a collection of directions.
     * 
     * @return a collection of directions
     */
    DirectionInterface reduce(final Collection<? extends DirectionInterface> directions);

    /**
     * Returns the named directions.
     * 
     * @return the named directions
     */
    List<NamedDirection> getNamedDirections();

    /**
     * Returns the dimension manager.
     * 
     * @return the dimension manager
     */
    DimensionInterface getDimensionManager();

}
