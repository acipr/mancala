package com.mancala;

import java.awt.Dimension;
import java.util.ArrayList;

public class BigPot extends Pot {
	
	private static final long serialVersionUID = -9024340637590238213L;
	
	public BigPot() {
		this.setPreferredSize(new Dimension(90,300));
	}

	@Override
	protected void initBeans() {
		beans = new ArrayList<Bean>();
		refresh();
	}
	
	@Override
	protected void createListener() {}

}
