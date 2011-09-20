
package fr.designpattern.zerosumgames.abstractions.immutable;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;

public interface ContextManagerInterface extends ImmutableInterface<ContextManagerInterface> {

    ContextInterface context();

    //ContextManagerInterface apply(ContextInterface context);

    ContextManagerInterface apply(ContextInterface context, Map<Object, Object> symbols);

    void start();

    void pause();

    void resume();

}