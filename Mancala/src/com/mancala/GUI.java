package com.mancala;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI {
	private Pot[] pots;
	private Pot bigPot1;
	private Pot bigPot2;
	private Mancala mancala;
	
	public GUI(Mancala mancala) {
		this.mancala = mancala;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Mancala");
		frame.setSize(800, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		frame.setVisible(true);
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		createPots();
		JPanel center = new JPanel(new GridLayout(2, 6));
		center.setOpaque(false);
		MyPanel panel = new MyPanel();
		panel.setLayout(new BorderLayout());
		panel.add(center, BorderLayout.CENTER);
		addPots(center, panel);
		content.add(panel, BorderLayout.CENTER);
		createMenu(frame);
	}
	
	private void createMenu(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(menu);
		JMenuItem newGame = new JMenuItem("New Game");
		menu.add(newGame);
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mancala.resetGame();
			}
		});
	}

	private void addPots(JPanel center, MyPanel panel) {
		panel.add(bigPot1, BorderLayout.EAST);
		panel.add(bigPot2, BorderLayout.WEST);
		for (int i = 11; i > 5; i--) {
			center.add(pots[i]);
		}
		
		for (int i = 0; i < 6; i++) {
			center.add(pots[i]);
		}
	}

	private void createPots() {
		bigPot1 = new BigPot();
		bigPot1.setPit(mancala.getPlayerOne().getBigPit());
		bigPot2 = new BigPot();
		bigPot2.setPit(mancala.getPlayerTwo().getBigPit());
		pots = new Pot[12];
		for (int i = 0; i < pots.length; i++) {
			pots[i] = new Pot();
			if (i < 6) {
				pots[i].setPit(mancala.getPlayerOne().getSmallPits()[i]);
			} else {
				pots[i].setPit(mancala.getPlayerTwo().getSmallPits()[i - 6]);
			}
		}
	}
	

}
