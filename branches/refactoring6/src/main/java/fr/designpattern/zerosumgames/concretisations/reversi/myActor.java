package fr.designpattern.zerosumgames.concretisations.reversi;

import akka.actor.UntypedActor;

class myActor extends UntypedActor {

	@Override
	public void onReceive(final Object signal) {
		((SignalInterface) signal).process();
	}

}