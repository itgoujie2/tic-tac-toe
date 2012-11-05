package com.ttt.client;

import java.io.Serializable;

public class Move implements Serializable{
	String[][] clicked;
	String move;
	
	public Move() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Move(String[][] clicked, String move) {
		super();
		this.clicked = clicked;
		this.move = move;
	}

	public String[][] getClicked() {
		return clicked;
	}

	public void setClicked(String[][] clicked) {
		this.clicked = clicked;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}
	
	
	
	
}
