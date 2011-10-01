package fr.designpattern.zerosumgames.abstractions.rendering.symbol;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;

//TODO refactoring possible avec CellInterface
public interface SymbolInterface<ID extends ImmutableInterface<ID>, VALUE extends ImmutableInterface<VALUE>> extends ImmutableInterface<SymbolInterface<ID, VALUE>> {

	public SymbolInterface<ID, VALUE> apply(VALUE newValue);

	public ID id();

	public VALUE value();

}