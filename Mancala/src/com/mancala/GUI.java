package com.mancala;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	Pot[] pots;
	Pot manc1;
	Pot manc2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI();

	}
	
	public GUI() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Mancala");
		frame.setSize(800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		frame.setVisible(true);
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		createPots();
		JPanel center = new JPanel(new GridLayout(2, 6));
		center.setOpaque(false);
		addPots(center);
		manc1 = new Pot();
		manc2 = new Pot();
		manc1.setPreferredSize(new Dimension(90,300));
		manc2.setPreferredSize(new Dimension(90,300));
		MyPanel panel = new MyPanel();
		panel.setLayout(new BorderLayout());
		panel.add(manc1, BorderLayout.WEST);
		panel.add(manc2, BorderLayout.EAST);
		panel.add(center, BorderLayout.CENTER);
		content.add(panel, BorderLayout.CENTER);
	}

	private void addPots(JPanel center) {
		for (int i = 0; i < pots.length; i++) {
			center.add(pots[i]);
		}
	}

	private void createPots() {
		pots = new Pot[12];
		for (int i = 0; i < pots.length; i++) {
			pots[i] = new Pot();
		}
	}
	

}
