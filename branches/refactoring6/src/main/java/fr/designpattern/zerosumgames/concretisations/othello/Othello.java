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

package fr.designpattern.zerosumgames.concretisations.othello;

import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.designpattern.zerosumgames.abstractions.ContextManager;
import fr.designpattern.zerosumgames.abstractions.ContextManagerInterface;
import fr.designpattern.zerosumgames.abstractions.context.Context;
import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;

class Othello {

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {
        task1();
        //task2();
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void task1() {
        /*-------------------------------------8<-------------------------------------*/
        final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("othello-context.xml"));
        /*-------------------------------------8<-------------------------------------*/
        final ContextInterface gameplay = (Context) factory.getBean("gamePlay");
        /*-------------------------------------8<-------------------------------------*/
        @SuppressWarnings("unchecked")
        final Map<Object, Object> symbols = (Map<Object, Object>) factory.getBean("symbols");
        /*-------------------------------------8<-------------------------------------*/
        // TODO charger directement un bean ContextManager
        final ContextManagerInterface contextManager = ContextManager.from(gameplay, symbols);
        /*-------------------------------------8<-------------------------------------*/
        contextManager.start();
        /*-------------------------------------8<-------------------------------------*
        final SignalInterface signal = new mySignal(contextManager);
        final RemoteServerModule remoteServerModule = Actors.remote().start();
        remoteServerModule.register("hello-service", Actors.actorOf(myActor.class));
        final ActorRef actor = Actors.remote().actorFor("hello-service", "localhost", 2552);
        actor.sendOneWay(signal);
        System.out.println("\n It's in the pipe !\n");
        /*-------------------------------------8<-------------------------------------*
        final SignalInterface signal2 = new mySignal(contextManager);
        final RemoteServerModule remoteServerModule2 = Actors.remote().start();
        remoteServerModule2.register("hello-service", Actors.actorOf(myActor.class));
        final ActorRef actor2 = Actors.remote().actorFor("hello-service", "localhost", 2552);
        actor2.sendOneWay(signal2);
        System.out.println("\n It's in the pipe !\n");
        /*-------------------------------------8<-------------------------------------*/
    }

    public static void task2() {

        /*
        final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("reversi-context2.xml"));


        final GameServiceInterface gameService = GameService.from();
        final GamePlayServiceInterface gameplayServive = GamePlayService.from(gameService);
        final GamePlayManagerServiceInterface gamePlayManagerService = new GamePlayManagerService(gameplayServive); // TODO ?! from()
        final ContextInterface gameplay = (Context) factory.getBean("gamePlay");
        final ContextInterface gameplayManager = Context.from(gameplay);

        @SuppressWarnings("unchecked")
        final Map<Object, Object> symbols = (Map<Object, Object>) factory.getBean("symbols");
        symbols.put(gameplay.game().board().cell(1, 1), "1,1");
        symbols.put(gameplay.game().board().cell(1, 8), "1,8");
        symbols.put(gameplay.game().board().cell(8, 1), "8,1");
        symbols.put(gameplay.game().board().cell(8, 8), "8,8");


        final SignalInterface signal = new mySignal(gamePlayManagerService, gameplayManager, symbols);
        final RemoteServerModule remoteServerModule = Actors.remote().start();
        remoteServerModule.register("hello-service", Actors.actorOf(myActor.class));
        final ActorRef actor = Actors.remote().actorFor("hello-service", "localhost", 2552);
        actor.sendOneWay(signal);
        System.out.println("\n main2 is in the pipe !\n");
        
        */

    }

}