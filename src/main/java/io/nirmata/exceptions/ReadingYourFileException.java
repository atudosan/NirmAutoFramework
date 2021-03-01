package io.nirmata.exceptions;

@SuppressWarnings("serial")
public class ReadingYourFileException extends FrameworkException {

	public ReadingYourFileException(String message) {
		super(message);
	}
	
	public ReadingYourFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
