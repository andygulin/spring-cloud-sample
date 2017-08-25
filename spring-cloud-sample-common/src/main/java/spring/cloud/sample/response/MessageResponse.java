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
}