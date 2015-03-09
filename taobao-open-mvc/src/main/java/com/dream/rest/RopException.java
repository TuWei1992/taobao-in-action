/**
 *
 * 
 */
package com.dream.rest;

/**
 * <pre>
 *   ROP的异常。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class RopException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7575558902202751438L;

	public RopException() {
    }

    public RopException(String message) {
        super(message);
    }

    public RopException(String message, Throwable cause) {
        super(message, cause);
    }

    public RopException(Throwable cause) {
        super(cause);
    }
}

