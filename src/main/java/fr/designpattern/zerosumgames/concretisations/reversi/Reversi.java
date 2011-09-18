/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.concretisations.reversi;


import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import akka.actor.ActorRef;
import akka.actor.Actors;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManager;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerService;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlay;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayService;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameService;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.GameServiceInterface;

class Reversi {

	private static ActorRef actor;

	public static void main(final String[] args) {

		Actors.remote().start("localhost", 9999).register("hello-service", Actors.actorOf(myActor.class));

		actor = Actors.remote().actorFor("hello-service", "localhost", 9999);

		main1(args);
		main2(args);

	}


	public static void main1(final String[] args) {

		/*-------------------------------------8<-------------------------------------*/

		final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("reversi-context1.xml"));

		/*-------------------------------------8<-------------------------------------*/

		final GameServiceInterface gameService = GameService.from();
		final GamePlayServiceInterface gameplayServive = GamePlayService.from(gameService);
		final GamePlayManagerServiceInterface gamePlayManagerService = new GamePlayManagerService(gameplayServive); // TODO ?! from()

		final GamePlayInterface gameplay = (GamePlay)factory.getBean("gamePlay");
		final GamePlayManagerInterface gameplayManager = GamePlayManager.from(gameplay);

		/*-------------------------------------8<-------------------------------------*/

		@SuppressWarnings("unchecked")
		final Map<Object, Object> symbols = (Map<Object, Object>) factory.getBean("symbols");
		symbols.put(gameplay.game().board().cell(1, 1), "1,1");
		symbols.put(gameplay.game().board().cell(1, 6), "1,6");
		symbols.put(gameplay.game().board().cell(6, 1), "6,1");
		symbols.put(gameplay.game().board().cell(6, 6), "6,6");

		/*-------------------------------------8<-------------------------------------*/

		//gamePlayManagerService.start(gameplayManager, symbols);

		//TODO emballer tout ça dans un object Action immutable (appeler process())

		final mySignal signal = new mySignal(gamePlayManagerService, gameplayManager, symbols);

		/*-------------------------------------8<-------------------------------------*/



		/*-------------------------------------8<-------------------------------------*/

		/*
		final Object res = actor.sendRequestReply(signal);
		System.out.println(res);
		 */
		actor.sendOneWay(signal);

		/*-------------------------------------8<-------------------------------------*/

		//System.out.println("\nIt's in the pipe !\n");
		System.out.println("\n main1 is in the pipe !\n");

		/*-------------------------------------8<-------------------------------------*/

		/*
		long t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < 3000) ;
		gamePlayManagerService.pause();


		t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < 6000) ;
		gamePlayManagerService.resume();

		/*-------------------------------------8<-------------------------------------*/

		/*
		int i = 1;
		while(!gamePlayManagerService.isOver()) {
			final Scanner scanner = new Scanner(System.in);
			try {
				i = scanner.nextInt();
				if (i == 0)
					gamePlayManagerService.pause();
				else if(i == 1)
					gamePlayManagerService.resume();
				else
					throw new InputMismatchException();
			}
			catch (final InputMismatchException e) {
				System.out.println("Please try again...");
			}
		}
		System.out.println("It's over!");
		/*-------------------------------------8<-------------------------------------*/
	}


	public static void main2(final String[] args) {

		/*-------------------------------------8<-------------------------------------*/

		final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("reversi-context2.xml"));

		/*-------------------------------------8<-------------------------------------*/

		final GameServiceInterface gameService = GameService.from();
		final GamePlayServiceInterface gameplayServive = GamePlayService.from(gameService);
		final GamePlayManagerServiceInterface gamePlayManagerService = new GamePlayManagerService(gameplayServive); // TODO ?! from()

		final GamePlayInterface gameplay = (GamePlay)factory.getBean("gamePlay");
		final GamePlayManagerInterface gameplayManager = GamePlayManager.from(gameplay);

		/*-------------------------------------8<-------------------------------------*/

		@SuppressWarnings("unchecked")
		final Map<Object, Object> symbols = (Map<Object, Object>) factory.getBean("symbols");
		symbols.put(gameplay.game().board().cell(1, 1), "1,1");
		symbols.put(gameplay.game().board().cell(1, 8), "1,8");
		symbols.put(gameplay.game().board().cell(8, 1), "8,1");
		symbols.put(gameplay.game().board().cell(8, 8), "8,8");

		/*-------------------------------------8<-------------------------------------*/

		//gamePlayManagerService.start(gameplayManager, symbols);

		//TODO emballer tout ça dans un object Action immutable (appeler process())

		final mySignal signal = new mySignal(gamePlayManagerService, gameplayManager, symbols);

		/*-------------------------------------8<-------------------------------------*/

		//actor = remote().actorFor("hello-service", "localhost", 9999);

		/*-------------------------------------8<-------------------------------------*/

		/*
		final Object res = actor.sendRequestReply(signal);
		System.out.println(res);
		 */
		actor.sendOneWay(signal);

		/*-------------------------------------8<-------------------------------------*/

		//System.out.println("\nIt's in the pipe !\n");
		System.out.println("\n main2 is in the pipe !\n");

		/*-------------------------------------8<-------------------------------------*/

		/*
		long t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < 3000) ;
		gamePlayManagerService.pause();


		t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < 6000) ;
		gamePlayManagerService.resume();

		/*-------------------------------------8<-------------------------------------*/
		/*
		int i = 1;
		while(!gamePlayManagerService.isOver()) {
			final Scanner scanner = new Scanner(System.in);
			try {
				i = scanner.nextInt();
				if (i == 0)
					gamePlayManagerService.pause();
				else if(i == 1)
					gamePlayManagerService.resume();
				else
					throw new InputMismatchException();
			}
			catch (final InputMismatchException e) {
				System.out.println("Please try again...");
			}
		}
		System.out.println("It's over!");
		/*-------------------------------------8<-------------------------------------*/

	}


}