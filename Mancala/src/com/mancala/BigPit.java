package com.mancala;


public class BigPit extends Pit {
	
	public BigPit(Player owner) {
		this.owner = owner;
	}

	@Override
	public Pit getNext() {
		return next;
	}

	@Override
	void moveCounters() {
		throw new RuntimeException("Moved counters from big pot");
	}

}
