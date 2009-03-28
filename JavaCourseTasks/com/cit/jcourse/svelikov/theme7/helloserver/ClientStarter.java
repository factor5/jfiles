package com.cit.jcourse.svelikov.theme7.helloserver;

public class ClientStarter {

	/**
	 * Starts the client on the specifyed port.
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client(8001);
		client.startClient();
	}
}
