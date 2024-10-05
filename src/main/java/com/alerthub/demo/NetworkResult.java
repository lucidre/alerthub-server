package com.alerthub.demo;

public class NetworkResult {
    final String message;
    final Object data;

    public NetworkResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetworkResult [message=" + message + ", data=" + data + "]";
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}