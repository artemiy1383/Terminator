package net.terminator.network;

public class ServerResponse<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;

    public ServerResponse(boolean success, T data, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(true, data, null);
    }

    public static <T> ServerResponse<T> failure(String errorMessage) {
        return new ServerResponse<>(false, null, errorMessage);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
