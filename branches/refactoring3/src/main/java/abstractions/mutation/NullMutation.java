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

package abstractions.mutation;

// TODO à virer...
public class NullMutation extends AbstractMutation {

    private final static MutationInterface INSTANCE = new NullMutation();

    public final static MutationInterface getInstance() {
        return NullMutation.INSTANCE;
    }

    // TODO ...sinon, injecter la cellule nulle et le type nul
    public NullMutation() {
        super(null, null);
    }

    @Override
    public void process() {}

    @Override
    public void cancel() {}

}
