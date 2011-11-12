package com.mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class BigPot extends Pot {
	
	private static final long serialVersionUID = -9024340637590238213L;
	
	public BigPot() {
		super(true);
		this.setPreferredSize(new Dimension(120,320));
	}

	@Override
	protected void initBeans() {
		beans = new ArrayList<Bean>();
		refresh();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawString(pit.owner.getName(), 30, 20);
	}
	
	@Override
	protected void createListener() {}

}
