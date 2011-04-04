
package abstractions.cell.mutation;

import abstractions.cell.CellInterface;

//TODO utiliser la factory
//TODO ajouter un cache
public class MutationFactory {

    public static AtomicMutationInterface birth(CellInterface cell) {
        return new Birth(cell);
    }

    public static AtomicMutationInterface death(CellInterface cell) {
        return new Birth(cell);
    }

    public static AtomicMutationInterface alteration(CellInterface cell) {
        return new Alteration(cell);
    }

}
