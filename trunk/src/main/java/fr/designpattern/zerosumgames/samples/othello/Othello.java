/*
 * Copyright 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.samples.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.AbstractGame;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.Pieces;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

// TODO améliorer la fonction d'évaluation en prenant en compte le nombre de
// cellules voisines à celle jouée
public class Othello extends AbstractGame {

    // ------------------------------------------------------------
    public static final Class<OthelloPieceTypes> PIECE_TYPES = OthelloPieceTypes.class;
    public static final BoardDimensions BOARD_DIMENSION = new BoardDimensions(1, 8, 1, 8);

    //private transient int nullMoveHasBeenPlayed = 0;
    // ------------------------------------------------------------
    private void setupBoard(final GameBoardInterface board) {
        board.cell(4, 5).setPiece(
                this.piece(OpponentsEnumeration.FIRST_PLAYER));
        board.cell(5, 4).setPiece(
                this.piece(OpponentsEnumeration.FIRST_PLAYER));
        board.cell(4, 4).setPiece(
                this.piece(OpponentsEnumeration.SECOND_PLAYER));
        board.cell(5, 5).setPiece(
                this.piece(OpponentsEnumeration.SECOND_PLAYER));
    }

    // ------------------------------------------------------------
    public Othello(final GameBoardInterface board) {
        super(new Pieces(Othello.PIECE_TYPES), board);// TODO !! à revoir
        this.setupBoard(board);
    }

    // ------------------------------------------------------------
    @Override
    public boolean hasNullMove() {
        return true;
    }

    // ------------------------------------------------------------
    @Override
    public final List<LegalMoveInterface> getLegalMoves(
            final OpponentsEnumeration side) {
        final List<LegalMoveInterface> legalMoves = new ArrayList<LegalMoveInterface>();
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (final BoardCellInterface cell : line) {
                if (this.canPlayHere(cell, side)) {
                    legalMoves.add(this.makeMove(side, cell.getPosition()));
                }
            }
        }
        // TODO ? cache du nullMove pour chaque side
        legalMoves.add(this.makeMove(side, this.cell(null).getPosition()));
        return legalMoves;
    }

    // ------------------------------------------------------------
    @Override
    public boolean undoMove(final LegalMoveInterface playedMove) {
        if (!playedMove.isNull()) {
            this.cell(playedMove.getPosition()).setPiece(null); //TODO ? utiliser la pièce nulle
            final OthelloMove othelloMove = (OthelloMove) playedMove;
            this.revertCells(othelloMove.getCellsToRevert());
        }
        else {
            //--this.nullMoveHasBeenPlayed;
        }
        return true; // is move undone ?
    }

    // ------------------------------------------------------------
    private boolean isGameOver(final LegalMoveInterface previousMove) {
        boolean isGameOver = false;
        // Game Over si deux coups nuls consécutifs
        //if(this.nullMoveHasBeenPlayed > 1) {
        //isGameOver = true;
        //System.out.println("\ndeux coups nuls consécutifs");
        //}
        // Game Over s'il ne reste que le coup nul pour chacun des joueurs
        /* else */if (this.getLegalMoves(OpponentsEnumeration.FIRST_PLAYER)
                .size() == 1
                && this.getLegalMoves(OpponentsEnumeration.SECOND_PLAYER)
                        .size() == 1) {
            isGameOver = true;
            //System.out.println("\nplus de coups légaux");
        }
        return isGameOver;
    }

    // ------------------------------------------------------------
    private PieceInterface piece(final OpponentsEnumeration player) {
        return super.piece(player, OthelloPieceTypes.PAWN);
    }

    // ------------------------------------------------------------
    private boolean isNeighbourCellHavingOpponentPiece(
            final BoardCellInterface neighbourCell, final OpponentsEnumeration side) {
        // Est-ce que la cellule : (existe, n'est pas vide, contient une pièce de l'adversaire)
        return !neighbourCell.isNull()
                && !neighbourCell.isEmpty()
                && neighbourCell.getPiece().getSide() == OpponentsEnumeration
                        .opponent(side);
        // TODO utiliser la pièce nulle et la celulle nulle
    }

    // ------------------------------------------------------------
    private boolean isBoundableInThisDirection(
            final Entry<BoardCardinalPosition, BoardCellInterface> neighbourEntry) {
        boolean isBoundable = false;
        final BoardCardinalPosition direction = neighbourEntry.getKey();
        final OpponentsEnumeration opponent = OpponentsEnumeration
                .opponent(neighbourEntry.getValue().getPiece().getSide());
        BoardCellInterface neighbourCell = neighbourEntry.getValue().getNeighbour(
                direction);
        // tant qu'une cellule voisine existe et qu'elle n'est pas vide
        while (!neighbourCell.isNull() && !neighbourCell.isEmpty()) {
            // si la cellule voisine contient une pièce de l'adversaire
            if (neighbourCell.getPiece().getSide() == opponent) {
                isBoundable = true;
                break;
            }
            neighbourCell = neighbourCell.getNeighbour(direction);
        }
        return isBoundable;
    }

    // ------------------------------------------------------------
    // TODO ? rajouter à l'interface
    public boolean canPlayHere(final BoardCellInterface cell,
            final OpponentsEnumeration side) {
        boolean canPlayHere = false;
        // si la cellule est vide
        if (cell.isEmpty()) {
            //si la cellule est vide, les cellules voisines sont inspectées
            for (final Entry<BoardCardinalPosition, BoardCellInterface> cellNeighbourEntry : cell
                    .getNeighbourhood().entrySet()) {
                // si une des cellules voisines contient au moins une pièce de l'adversaire
                // et qu'une pièce du joueur se trouve à l'extrémité d'une série continue de pièces de l'adversaire
                if (this.isNeighbourCellHavingOpponentPiece(
                        cellNeighbourEntry.getValue(), side)
                        && this.isBoundableInThisDirection(cellNeighbourEntry)) {
                    canPlayHere = true;
                    break;
                }
            }
        }
        return canPlayHere;
    }

    // ------------------------------------------------------------
    // TODO ? implémentation par défaut dans la classe abstraite
    private LegalMoveInterface makeMove(final OpponentsEnumeration side,
            final BoardPositionInterface position) {
        // TODO utiliser un cache
        return new OthelloMove(side, position);
    }

    // ------------------------------------------------------------
    private List<BoardCellInterface> computeCellsToRevert(
            final LegalMoveInterface move) {
        final OpponentsEnumeration side = move.getSide();
        final BoardCellInterface cell = this.cell(move.getPosition());
        final List<BoardCellInterface> cellsToRevert = new ArrayList<BoardCellInterface>();
        final List<BoardCellInterface> opponentCells = new ArrayList<BoardCellInterface>();
        BoardCellInterface neighbourCell;
        for (final Entry<BoardCardinalPosition, BoardCellInterface> cellNeighbourEntry : cell
                .getNeighbourhood().entrySet()) {
            //si la cellule voisine ne contient pas une pièce de l'adversaire
            if (!this.isNeighbourCellHavingOpponentPiece(
                    cellNeighbourEntry.getValue(), side)) {
                continue;
            }
            opponentCells.clear();
            opponentCells.add(cellNeighbourEntry.getValue());
            neighbourCell = cellNeighbourEntry.getValue().getNeighbour(
                    cellNeighbourEntry.getKey());
            // tant qu'une cellule voisine existe
            while (!neighbourCell.isNull()) {
                // si la cellule voisine est vide
                if (neighbourCell.isEmpty()) {
                    break;
                }
                // si la cellule voisine contient une pièce du joueur
                if (neighbourCell.getPiece().getSide() == side) {
                    cellsToRevert.addAll(opponentCells);
                    break;
                }
                else {
                    opponentCells.add(neighbourCell);
                }
                neighbourCell = neighbourCell.getNeighbour(cellNeighbourEntry
                        .getKey());
            }
        }
        return cellsToRevert;
    }

    // ------------------------------------------------------------
    private void revertCells(final List<BoardCellInterface> cellsToRevert) {
        final PieceInterface piece = this.piece(OpponentsEnumeration
                .opponent(cellsToRevert.get(0).getPiece().getSide()));
        for (final BoardCellInterface cell : cellsToRevert) {
            cell.setPiece(piece);
        }
    }

    // ------------------------------------------------------------
    @Override
    public boolean doMove(final LegalMoveInterface moveToPlay) {
        final OthelloMove othelloMove = (OthelloMove) moveToPlay;

        if (!othelloMove.isNull()) {

            //this.nullMoveHasBeenPlayed = 0;

            final PieceInterface playerPiece = this.piece(
                    othelloMove.getSide(), OthelloPieceTypes.PAWN);
            this.cell(othelloMove.getPosition()).setPiece(playerPiece);
            othelloMove
                    .setCellsToRevert(this.computeCellsToRevert(othelloMove));
            this.revertCells(othelloMove.getCellsToRevert());
        }
        else {
            //++this.nullMoveHasBeenPlayed;
            ///System.out.println("coup nul joué : " + this.nullMoveHasBeenPlayed);
        }

        ////System.out.println("delta pour " + othelloMove.getSide() + this.computeDelta(othelloMove.getSide()) );
        return true; // is move done ?
    }

    // ------------------------------------------------------------
    // TODO à optimiser
    public int computeDelta(final OpponentsEnumeration side) {
        int delta = 0;
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (final BoardCellInterface cell : line) {
                if (cell.isNull() || cell.isEmpty()) {
                    continue;
                }
                if (cell.getPiece().getSide().equals(side)) {
                    ++delta;
                }
                else {
                    --delta;
                }
            }
        }
        return delta;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromVictory(final LegalMoveInterface previousMove) {
        return this.isGameOver(previousMove)
                && this.computeDelta(previousMove.getSide()) > 0;
    }

    // ------------------------------------------------------------
    @Override
    public boolean isGameOverFromDraw(final LegalMoveInterface previousMove) {
        return this.isGameOver(previousMove)
                && this.computeDelta(previousMove.getSide()) == 0;
    }

    // ------------------------------------------------------------
    @Override
    public double computeStaticEvaluation(final LegalMoveInterface move) {
        if (move.isNull()) {
            return this.computeDelta(move.getSide());
        }
        int n = 1;
        for (final Entry<BoardCardinalPosition, BoardCellInterface> cellNeighbourEntry : this
                .cell(move.getPosition()).getNeighbourhood().entrySet()) {
            if (cellNeighbourEntry.getValue().isNull()) {
                n += 1;
            }
        }
        return this.computeDelta(move.getSide()) + n;
    }
    // ------------------------------------------------------------
}