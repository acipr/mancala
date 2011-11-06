package com.mancala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

public class Pot extends JComponent {
	
	private static final long serialVersionUID = 4648914916326942097L;
	private boolean mouseOver;
	private boolean beansInitialized;
	private List<Bean> beans;
	
	public Pot() {
		mouseOver = false;
		beansInitialized = false;
		createListener();
	}

	private void initBeans() {
		beans = new ArrayList<Bean>();
		Random rnd = new Random();
		int x;
		int y;
		for(int i = 0; i < 10; i++) {
			x = (int) ((getWidth() - 15) * 0.25) + rnd.nextInt((int) ((getWidth() - 15) * 0.5));
			y = (int) ((getHeight() - 15) * 0.25) + rnd.nextInt((int) ((getHeight() - 15) * 0.5));
			Bean bean = new Bean(1.0 * x / getWidth(), 1.0 * y / getHeight());
			if(isSuitable(bean))
				beans.add(bean);
			else
				i--;
		}
	}

	private void createListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOver = true;
				refresh();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
				refresh();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (!mouseOver) {
			g2d.setColor(new Color(92, 51, 23));
		} else {
			g2d.setColor(new Color(139, 69, 19));
		}
		g2d.setStroke(new BasicStroke(5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		g2d.drawOval((int) (getWidth() * 0.1), (int) (getHeight() * 0.1), (int) (getWidth() * 0.8), (int) (getHeight() * 0.8));
		g2d.setColor(new Color(255, 69, 0));
		g2d.setStroke(new BasicStroke(0.1F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		if(!beansInitialized) {
			beansInitialized = true;
			initBeans();
		}
		for(Bean bean : beans) {
			int x = (int) (bean.getX() * getWidth());
			int y = (int) (bean.getY() * getHeight());
			GradientPaint gp = new GradientPaint(x + 2, y + 2, new Color(/*255, 69, 0*/255, 81, 71), x + 13, y + 13, new Color(139, /*37*/9, 0), false);
			g2d.setPaint(gp);
			g2d.fillOval(x, y, 15, 15);
		}
	}
	
	private boolean isSuitable(Bean bean) {
		for(Bean b : beans) {
			if(b.distanceFrom(bean, getWidth(), getHeight()) < 10.0) {
//				System.out.println("Too close!");
				return false;
			}
		}
		return true;
	}
	
	private void refresh() {
		this.repaint();
	}

}
