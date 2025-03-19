package com.example.onecar.dto.response;

public class OneCarHttpResponse<T> {
    public enum Status {
        SUCCESS,
        BAD_REQUEST,
        FAILED,
        INTERNAL_SERVER_ERROR
    }

    private String message;

    private Status status;

    private T object;

    public static <T> Builder<T> builder() {
        return new Builder<>(new OneCarHttpResponse<>());
    }

    public static class Builder<T> {
        private final OneCarHttpResponse<T> ptr;

        public Builder(OneCarHttpResponse<T> p) {
            ptr = p;
        }

        public Builder<T> message(String message) {
            ptr.setMessage(message);
            return this;
        }

        public Builder<T> status(Status status) {
            ptr.setStatus(status);
            return this;
        }

        public Builder<T> object(T obj) {
            ptr.setObject(obj);
            return this;
        }

        public OneCarHttpResponse<T> build() {
            return ptr;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public int getCode() {
        switch(status) {
            case SUCCESS -> {
                return 200;
            }

            case FAILED -> {
                return 402;
            }

            case BAD_REQUEST -> {
                return 400;
            }

            case INTERNAL_SERVER_ERROR -> {
                return 500;
            }
        }

        return 0;
    }
}
