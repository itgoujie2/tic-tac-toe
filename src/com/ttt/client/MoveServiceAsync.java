package com.ttt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MoveServiceAsync {
	void calculateMove(String[][] clicked, AsyncCallback<Move> callback)
			throws IllegalArgumentException;
}
