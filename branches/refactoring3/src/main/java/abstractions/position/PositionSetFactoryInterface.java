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

package abstractions.position;

import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

interface PositionSetFactoryInterface {

    /**
     * Returns an unmodifiable set of new positions for a given dimension.
     * 
     * @param dimension
     *            a given dimension
     * 
     * @return an unmodifiable set of new positions for a given dimension.
     */
    public Set<PositionInterface> newPositionSet(final DimensionInterface dimension);

}
