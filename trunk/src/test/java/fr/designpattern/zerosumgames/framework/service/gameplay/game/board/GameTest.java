package fr.designpattern.zerosumgames.framework.service.gameplay.game.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilderInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;

public class GameTest {
	
	private GameInterface game;

	@Before
	public void setUp() {
		final GameBuilderInterface gameBuilder = new GameBuilder(Tictactoe.class);
		this.game = gameBuilder.build();
	}

	@Test
	public void testCopyConstructor() {
		//TODO
	}

	@After
	public void tearDown() {}

}
