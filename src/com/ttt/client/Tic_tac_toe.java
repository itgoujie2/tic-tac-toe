package com.ttt.client;

import com.ttt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tic_tac_toe implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final MoveServiceAsync moveService = GWT
			.create(MoveService.class);
	
	//using a int to track who should have the first click
			//1 -- human
			//0 -- computer
	private int trackWhoFirst = 0;
	private String[][] clicked = new String[3][3];
			
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		@SuppressWarnings("deprecation")
		ClickListener listener = new MyListener(); 
		
		Grid grid = new Grid(3,3);
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++){
				Button button = new Button();
				button.getElement().setId(Integer.toString(i)+Integer.toString(j));
				button.addClickListener(listener);
				grid.setWidget(i, j, button);
			}
		RootPanel.get("grid").add(grid);
		
		Button newGameButton = new Button("New Game");
		newGameButton.setPixelSize(160,50);
		newGameButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				++trackWhoFirst;
				newGame(trackWhoFirst%2);
			}
		});
		RootPanel.get("newGame").add(newGameButton);
	}
	
	private void newGame(int trackWhoFirst){
		clearGrid();
		if (trackWhoFirst == 0) computerMove(clicked);
	}
	
	private void clearGrid(){
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
				clicked[i][j] = "";
		
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++){
				DOM.getElementById(Integer.toString(i)+Integer.toString(j)).setInnerText("");
			}
	}
	
	private void computerMove(final String[][] clicked){
		AsyncCallback<Move> callback = new AsyncCallback<Move>(){
			

			public void onFailure(Throwable caught){
				
			}
			
			public void onSuccess(Move result){
				String[][] tempClicked = result.getClicked();
				transferStatus(tempClicked, clicked);
				String tempMove = result.getMove();
				int x = Integer.parseInt(tempMove.substring(0,1));
				int y = Integer.parseInt(tempMove.substring(1,2));
				move(tempMove,x,y);
			}
		};
		moveService.calculateMove(clicked, callback);
	}
	
	//this is for computer move
	private void move(String id, int x, int y){
		DOM.getElementById(id).setInnerText("O");
		clicked[x][y] = "O";
	}
	
	private static void transferStatus(String[][] tempClicked, String[][] clicked){
		for (int i=0;i<clicked.length;i++)
			for (int j=0;j<clicked.length;j++){
				clicked[i][j] = tempClicked[i][j];
			}
	}
	
	private class MyListener implements ClickListener{
		@Override
		@Deprecated
		public void onClick(Widget sender) {
			// TODO Auto-generated method stub
			Button button = (Button) sender;
			if (button.getText().equals("")){
				button.setText("X");
				String buttonId = button.getElement().getId();
				int x = Integer.parseInt(buttonId.substring(0,1));
				int y = Integer.parseInt(buttonId.substring(1,2));
				clicked[x][y] = "X";
			}
			computerMove(clicked);
		}

	}
	
}
