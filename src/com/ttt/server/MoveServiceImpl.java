package com.ttt.server;

import java.util.Random;

import com.ttt.client.Move;
import com.ttt.client.MoveService;
import com.ttt.shared.FieldVerifier;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MoveServiceImpl extends RemoteServiceServlet implements
		MoveService {

	public Move calculateMove(String[][] clicked){
		//check if there is a move for computer to win directly
		ComputerChecker checker1 = new ComputerChecker("O", clicked);
		//check if there is a move to block human from winning directly
		ComputerChecker checker2 = new ComputerChecker("X", clicked);
		//find a random move available
		String randomCell;
		Random random = new Random();
		while (true){
			int i = random.nextInt(3);
			int j = random.nextInt(3);
			if (clicked[i][j].equals("")) {
				randomCell = Integer.toString(i)+Integer.toString(j);
				break;
			}
		}
		
		if (checker1.check()!=null) return new Move(clicked,checker1.check());
		if (checker2.check()!=null) return new Move(clicked,checker2.check());
		return new Move(clicked, randomCell);
	}
}
