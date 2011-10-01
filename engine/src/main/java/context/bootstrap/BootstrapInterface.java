
package context.bootstrap;

import java.util.Map;

import context.ContextInterface;

import util.interfaces.ImmutableInterface;

public interface BootstrapInterface extends ImmutableInterface<BootstrapInterface> {

    ContextInterface context();

    //ContextManagerInterface apply(ContextInterface context);

    BootstrapInterface apply(ContextInterface context, Map<Object, Object> symbols);

    void run();

    void pause();

    void resume();

}