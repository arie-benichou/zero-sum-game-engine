package fr.designpattern.zerosumgames.concretisations.reversi;

import akka.actor.UntypedActor;

class myActor extends UntypedActor {

	@Override
	public void onReceive(final Object msg) {
		final mySignal signal = (mySignal) msg;
		signal.gamePlayManagerService().start(signal.gamePlayManager(), signal.symbols());
	}

}