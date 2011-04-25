
package abstractions.context;

import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManagerInterface;
import abstractions.side.SideInterface;

public interface ContextInterface {

    AdversityInterface getAdversity();

    CellManagerInterface getCellManager();

    void setSideToPlay(final SideInterface side);

    SideInterface getSideToPlay();

}