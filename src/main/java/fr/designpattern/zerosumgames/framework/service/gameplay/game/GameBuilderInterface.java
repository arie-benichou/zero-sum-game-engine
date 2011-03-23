package fr.designpattern.zerosumgames.framework.service.gameplay.game;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.DimensionInterface;

public interface GameBuilderInterface {

	GameBuilderInterface boardDimension(final DimensionInterface dimension);

	GameInterface build();

}