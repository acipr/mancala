package com.mancala;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI {
	private Pot[] pots;
	private Pot bigPot1;
	private Pot bigPot2;
	private Mancala mancala;
	
	public GUI(Mancala mancala) {
		this.mancala = mancala;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Mancala");
		frame.setSize(860, 320);
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
	
	public void refreshBigPots() {
		bigPot1.refresh();
		bigPot2.refresh();
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
				if (!mancala.getPlayerOne().isMovingBeans() && !mancala.getPlayerTwo().isMovingBeans()) {
					mancala.resetGame();
				}
			}
		});
		
		JMenu highScoresMenu = new JMenu("High Scores");
		menu.setMnemonic(KeyEvent.VK_H);
		JMenuItem highScores = new JMenuItem("Top 10");
		highScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mancala.getHighSCores().show();
			}
		});
		highScoresMenu.add(highScores);
		
		JMenuItem resethighScores = new JMenuItem("Reset");
		resethighScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mancala.getHighSCores().resetHighScores();
			}
		});
		highScoresMenu.add(resethighScores);
		menuBar.add(highScoresMenu);
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
			if (i < 6) {
				pots[i] = new Pot(true);
				pots[i].setPit(mancala.getPlayerOne().getSmallPits()[i]);
			} else {
				pots[i] = new Pot(false);
				pots[i].setPit(mancala.getPlayerTwo().getSmallPits()[i - 6]);
			}
		}
	}
	
	
	
	public GUI getGUI() {
		return this;
	}
}
