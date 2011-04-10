
package abstractions.cell.old;



// TODO enlever la visibilité publique
public final class Cell extends AbstractCell {

    @Override
    public String toString() {
        return this.willGenerateMutations() ? "(" + this.getPiece() + ")|" : " " + this.getPiece() + " |";
    }

}