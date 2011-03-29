
package game.cell;

import static game.piece.API.*;
import static game.position.API.*;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public final class API {

    public final static CellInterface NULL_CELL = new NullCell();

    /**
     * This is the interface for a game board cell.
     */
    public static interface CellInterface extends Comparable<CellInterface> {

        /**
         * Returns the position of this cell.
         * 
         * @return the position of this cell
         */
        PositionInterface getPosition();

        int getRow();

        int getColumn();

        /**
         * Returns true if this cell is empty.
         * 
         * @return true if this cell is empty
         */
        boolean isEmpty();

        /**
         * Returns the piece contained by this cell.
         * 
         * @return the piece contained by this cell
         */
        PieceInterface getPiece();

        /**
         * Assigns a piece to this cell.
         * 
         * @param piece
         *            the piece to be contained by this cell
         */
        void setPiece(final PieceInterface piece);

        boolean isNull();

    }

    public static interface CellFactoryInterface {

        CellInterface cell(final PositionInterface position);

        List<CellInterface> cells(final List<PositionInterface> positions);

        CellInterface clone(final CellInterface cellToClone);

        CellInterface nullCell();

    }

    public static final class CellFactory implements CellFactoryInterface {

        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */

        public static CellInterface NullCell() {
            return NULL_CELL;
        }

        public static final CellInterface Cell(final PositionInterface position) {
            return new Cell(position);
        }

        public static final List<CellInterface> Cells(final List<PositionInterface> positions) {
            final List<CellInterface> cells = Lists.newArrayListWithExpectedSize(positions.size());
            for (final PositionInterface position : positions) {
                cells.add(CellFactory.Cell(position));
            }
            return Collections.unmodifiableList(cells);
        }

        public static final CellInterface Clone(final CellInterface cellToClone) {
            final CellInterface clone = CellFactory.Cell(cellToClone.getPosition());
            clone.setPiece(cellToClone.getPiece());
            return clone;
        }

        /**
         * L'abjecte convention en question :p
         */

        private static final CellFactoryInterface INSTANCE = new CellFactory();

        private CellFactory() {}

        public static CellFactoryInterface getInstance() {
            return CellFactory.INSTANCE;
        }

        public CellInterface nullCell() {
            return CellFactory.NullCell();
        }

        public final List<CellInterface> cells(final List<PositionInterface> positions) {
            return CellFactory.Cells(positions);
        }

        public final CellInterface cell(final PositionInterface position) {
            return CellFactory.Cell(position);
        }

        public final CellInterface clone(final CellInterface cellToClone) {
            return CellFactory.Clone(cellToClone);
        }

    }

}