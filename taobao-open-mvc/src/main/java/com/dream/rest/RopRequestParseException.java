/**
 * 
 * 
 */
package com.dream.rest;

/**
 * <pre>
 *   对请求数据进行解析时发生异常
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class RopRequestParseException extends RopException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4373416989855658708L;
	private String requestMessage;

    public RopRequestParseException(String requestMessage) {
        this(requestMessage, "");
    }

    public RopRequestParseException(String requestMessage, String message) {
        this(requestMessage, message, null);
    }

    public RopRequestParseException(String requestMessage, String message, Throwable cause) {
        super(message, cause);
        this.setRequestMessage(requestMessage);
    }

    public RopRequestParseException(String requestMessage, Throwable cause) {
        this(requestMessage, null, cause);
    }

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
}

