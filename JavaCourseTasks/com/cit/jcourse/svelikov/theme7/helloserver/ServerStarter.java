package com.cit.jcourse.svelikov.theme7.helloserver;

public class ServerStarter {

	/**
	 * Starts the server on specifyed port.
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServer server = new HelloServer(8001);
		server.startServer();
	}
}
