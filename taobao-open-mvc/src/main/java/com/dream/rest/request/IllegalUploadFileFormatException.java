/**
 * 
 * 
 */
package com.dream.rest.request;

/**
 * <pre>
 *    上传文件字符串转换时发生错误
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class IllegalUploadFileFormatException extends IllegalArgumentException {

    public IllegalUploadFileFormatException() {
        super();
    }

    public IllegalUploadFileFormatException(String s) {
        super(s);
    }

    public IllegalUploadFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUploadFileFormatException(Throwable cause) {
        super(cause);
    }
}

