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

import abstractions.direction.DirectionManager.NamedDirection;

public interface DirectionManagerInterface {

    DirectionInterface getNamedDirection(final NamedDirection label);

    DirectionInterface getDirection(final int rowDelta, final int columnDelta);

    DirectionInterface reduce(final Collection<? extends DirectionInterface> directions);

    List<NamedDirection> getNamedDirections();

}
