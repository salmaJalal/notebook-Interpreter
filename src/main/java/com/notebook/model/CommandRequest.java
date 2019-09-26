package com.notebook.model;

/**
 * 
 * @author jalals
 *
 */
public class CommandRequest {

	private String code;
	private String sessionID;

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
