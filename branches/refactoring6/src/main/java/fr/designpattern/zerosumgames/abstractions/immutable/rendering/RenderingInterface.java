
package fr.designpattern.zerosumgames.abstractions.immutable.rendering;

public interface RenderingInterface<INPUT, OUTPUT> {

    OUTPUT render(INPUT input);
}
