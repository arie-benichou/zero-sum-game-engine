
package fr.designpattern.zerosumgames.abstractions.rendering;

public interface RenderingInterface<INPUT, OUTPUT> {

    OUTPUT render(INPUT input);
}
