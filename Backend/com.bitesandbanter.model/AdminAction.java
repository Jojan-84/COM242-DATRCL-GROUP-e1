// AdminAction.java

package com.bitesandbanter.model;
public class AdminAction {
    private final String type;
    private final Object data;
    private final String message;
    public AdminAction(String type, Object data, String message) {
        this.type = type; this.data = data; this.message = message;
    }
    public String getType() { return type; }
    public Object getData() { return data; }
    public String getMessage() { return message; }
}
