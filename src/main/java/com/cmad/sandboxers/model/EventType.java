package com.cmad.sandboxers.model;

/**
 * 
 * @author pkrishne
 *
 */
public enum EventType {
	
	ERROR("ERROR"), WARNING("WARNING"), NOTIFICATION("NOTIFICATION"), ALERT("ALERT"),  INFO("INFO"), DEBUG("DEBUG");

	private String type;

	EventType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}