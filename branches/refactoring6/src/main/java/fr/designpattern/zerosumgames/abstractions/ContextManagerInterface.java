
package fr.designpattern.zerosumgames.abstractions;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;

public interface ContextManagerInterface extends ImmutableInterface<ContextManagerInterface> {

    ContextInterface context();

    //ContextManagerInterface apply(ContextInterface context);

    ContextManagerInterface apply(ContextInterface context, Map<Object, Object> symbols);

    void start();

    void pause();

    void resume();

}