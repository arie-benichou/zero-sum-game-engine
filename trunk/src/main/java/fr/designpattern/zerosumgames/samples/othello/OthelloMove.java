/*
 * Copyright 2011 Arié Bénichou
 * 
 * This program isimport java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMove;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
 General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.samples.othello;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMove;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

// TODO ? implémenter une interface
public class OthelloMove extends LegalMove {
	// ---------------------------------------------------------------------
	private List<CellInterface> cellsToRevert;
	public final void setCellsToRevert(final List<CellInterface> revertedCells) {
		this.cellsToRevert = revertedCells;
	}
	public final List<CellInterface> getCellsToRevert() {
		return this.cellsToRevert;
	}
	// ---------------------------------------------------------------------
	public OthelloMove(final OpponentsEnumeration side, final PositionInterface position) {
		super(side, position);
	}
	// ---------------------------------------------------------------------
}