package org.darebeat.Utils;

public class SpringException extends RuntimeException {
    private String exceptionMsg;

    public SpringException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

	/**
	* Returns value of exceptionMsg
	* @return
	*/
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	/**
	* Sets new value of exceptionMsg
	* @param
	*/
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
