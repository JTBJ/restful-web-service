package com.in28minutes.rest.webservices.exception;

import java.util.Date;

public class ExceptionResponse {

	// == timestamp
	private Date timestamp;
	// == message
	private String message;
	// == detail
	private String details;
	// == personal message test
	private String myPersonalMessage = "James' exception check";
	
	public ExceptionResponse(Date timestamp, String message, String details, String myPersonalMessage) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.myPersonalMessage = myPersonalMessage;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}

	public String getMyPersonalMessage() {
		return myPersonalMessage;
	}

	public void setMyPersonalMessage(String myPersonalMessage) {
		this.myPersonalMessage = myPersonalMessage;
	}
	
}
