package com.mancala;

public class SmallPit extends Pit {

	protected SmallPit opposite;

	public SmallPit(Player owner) {
		this.owner = owner;
	}

	public int removeCounters() {
		int amount = counters;
		counters = 0;
		pot.removeBeans();
		return amount;
	}

	public void setOpposite(SmallPit pit) {
		opposite = pit;
	}

	public SmallPit getOpposite() {
		return opposite;
	}

	@Override
	public Pit getNext() {
		return next;
	}

	@Override
	void moveCounters() {
		int amount = removeCounters();
		Pit current = this;
		Pit newPit;
		while (amount > 0) {
			newPit = current.getNext();
			newPit.addCounters(1);
			current = newPit;
			amount--;
		}

		if (current instanceof SmallPit) {
			if (current.getCounters() == 1 && current.owner == this.owner) {
				this.owner.getBigPit().addCounters(
						((SmallPit) current).removeCounters()
								+ ((SmallPit) current).getOpposite()
										.removeCounters());
			}
			this.owner.endTurn();
			if (!this.owner.getMancala().checkWin()) {
				if (this.owner == this.owner.getMancala().getPlayerOne()) {
					this.owner.getMancala().getPlayerTwo().startTurn();
				} else {
					this.owner.getMancala().getPlayerOne().startTurn();
				}
			}
		}
	}
}
