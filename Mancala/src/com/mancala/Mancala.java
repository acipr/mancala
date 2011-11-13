package com.mancala;

import javax.swing.JOptionPane;

public class Mancala {
	private static final int PIT_COUNT = 6;
	
	private GUI gui;
	private Player playerOne;
	private Player playerTwo;
	private HighScores highScores;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Mancala mancala = new Mancala();
		mancala.initGame();
	}
	
	public boolean checkWin() {
		if(playerOne.hasCounters() && playerTwo.hasCounters())
			return false;
		else {
			if(playerOne.hasCounters()) {
				for(SmallPit s : playerOne.getSmallPits()) {
					playerOne.getBigPit().addCounters(s.removeCounters());
				}
			} else if(playerTwo.hasCounters()) {
				for(SmallPit s : playerTwo.getSmallPits()) {
					playerTwo.getBigPit().addCounters(s.removeCounters());
				}
			}
		}
		
		if(playerOne.getScore() > playerTwo.getScore()) {
			highScores.addHighSCore(playerOne.getName(), playerOne.getScore());
			JOptionPane.showMessageDialog(null, playerOne.getName() + " won", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		} else if(playerOne.getScore() < playerTwo.getScore()) {
			highScores.addHighSCore(playerTwo.getName(), playerTwo.getScore());
			JOptionPane.showMessageDialog(null, playerTwo.getName() + " won", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "It was a tie", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return true;
	}
	
	public void initGame() {
		initPlayers();
		gui = new GUI(this);
		changeNames();
		highScores = new HighScores();
	}
	
	public void changeNames() {
		String nameOne = JOptionPane.showInputDialog(null, "Enter name for player one (max 10 characters)", "Welcome to Mancala", JOptionPane.QUESTION_MESSAGE);
		if(nameOne != null && nameOne.length() <= 10)
			playerOne.setName(nameOne.replaceAll("\t", " "));
		String nameTwo = JOptionPane.showInputDialog(null, "Enter name for player two (max 10 characters)", "Welcome to Mancala", JOptionPane.QUESTION_MESSAGE);
		if(nameTwo != null && nameTwo.length() <= 10)
			playerTwo.setName(nameTwo.replaceAll("\t", " "));
		gui.refreshBigPots();
	}
	
	public void resetGame() {
		playerOne.reset();
		playerTwo.reset();
		playerOne.startTurn();
	}

	private void initPlayers() {
		playerOne = new Player(this);
		playerTwo = new Player(this);
		playerOne.setName("Player One");
		playerTwo.setName("Player Two");
		playerTwo.endTurn();
		playerOne.startTurn();
		initPits();
	}
	
	private void initPits() {
		SmallPit[] playerOnePits = new SmallPit[6];
		SmallPit[] playerTwoPits = new SmallPit[6];
		
		//create normal pits (1-5) for player 1
		for(int i = 0; i < PIT_COUNT - 1; i++) {
			playerOnePits[i] = new SmallPit(playerOne);
			playerOnePits[i].setCounters(4);
		}
		
		//create normal pits (1-5) for player 2
		for(int i = 0; i < PIT_COUNT - 1; i++) {
			playerTwoPits[i] = new SmallPit(playerTwo);
			playerTwoPits[i].setCounters(4);
		}
		
		//create last pits for players 1 & 2
		LastPit playerOneLastPit = new LastPit(playerOne);
		LastPit playerTwoLastPit = new LastPit(playerTwo);
		playerOneLastPit.setCounters(4);
		playerTwoLastPit.setCounters(4);
		
		//set alternates for last pits
		playerOneLastPit.setAlternate(playerTwoPits[0]);
		playerTwoLastPit.setAlternate(playerOnePits[0]);
		
		//add these to normal pits
		playerOnePits[PIT_COUNT - 1] = playerOneLastPit;
		playerTwoPits[PIT_COUNT - 1] = playerTwoLastPit;
		
		//add opposite pits
		for(int i = 0; i < PIT_COUNT; i++) {
			playerOnePits[i].setOpposite(playerTwoPits[PIT_COUNT - 1 - i]);
			playerTwoPits[i].setOpposite(playerOnePits[PIT_COUNT - 1 - i]);
		}
		
		//create big pits
		BigPit playerOneBigPit = new BigPit(playerOne);
		BigPit playerTwoBigPit = new BigPit(playerTwo);
		playerOneBigPit.setCounters(0);
		playerTwoBigPit.setCounters(0);
		playerOneBigPit.setNext(playerTwoPits[0]);
		playerTwoBigPit.setNext(playerOnePits[0]);
		
		//add next pits for player 1
		for(int i = 0; i < PIT_COUNT; i++) {
			if(i < PIT_COUNT - 1) {
				playerOnePits[i].setNext(playerOnePits[i + 1]);
			} else {
				playerOnePits[i].setNext(playerOneBigPit);
			}
		}
		
		//add next pits for player 2
		for(int i = 0; i < PIT_COUNT; i++) {
			if(i < PIT_COUNT - 1) {
				playerTwoPits[i].setNext(playerTwoPits[i + 1]);
			} else {
				playerTwoPits[i].setNext(playerTwoBigPit);
			}
		}
		
		//assign small pits
		playerOne.setSmallPits(playerOnePits);
		playerTwo.setSmallPits(playerTwoPits);
		
		//assign big pits
		playerOne.setBigPit(playerOneBigPit);
		playerTwo.setBigPit(playerTwoBigPit);
	}
	
	public Player getPlayerOne() {
		return playerOne;
	}
	
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	public HighScores getHighSCores() {
		return highScores;
	}

}