package com.ebc.definitions.session;

public class Session {

	private static SessionDTO session;

	public static SessionDTO getSession() {
		return session;
	}

	public static void setSession(SessionDTO session) {
		Session.session = session;
	}

}
