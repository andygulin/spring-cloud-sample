package spring.cloud.sample.response;

import java.io.Serializable;

public class MessageResponse implements Serializable {

    private boolean success;
    private Object data;

    private MessageResponse() {

    }

    private MessageResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static MessageResponse build(boolean success, Object data) {
        return new MessageResponse(success, data);
    }

    public static MessageResponse success(Object data) {
        return build(true, data);
    }

    public static MessageResponse fail(Object data) {
        return build(false, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}