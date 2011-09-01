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

package abstractions.selector;

import java.util.List;

import abstractions.mutation.MutationInterface;

public interface SelectorInterface {

    // TODO revoir l'interface: devrait retourner un seul élément
    //List<MutationInterface> applySelection(final List<MutationInterface> mutations);
    MutationInterface applySelection(final List<MutationInterface> mutations);

}