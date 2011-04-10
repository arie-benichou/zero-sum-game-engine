
package abstractions.cell.old;

public final class NullCell extends AbstractCell {

    @Override
    public boolean willGenerateMutations() {
        return false;
    }

}