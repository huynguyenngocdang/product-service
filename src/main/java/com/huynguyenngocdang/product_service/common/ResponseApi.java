package com.huynguyenngocdang.product_service.common;

import static com.huynguyenngocdang.product_service.constants.ApiResponse.ERROR_MESSAGE;
import static com.huynguyenngocdang.product_service.constants.ApiResponse.SUCCESS_CODE;
import static com.huynguyenngocdang.product_service.constants.ApiResponse.SUCCESS_MESSAGE;

public record ResponseApi<T>(ResponseStatus status, T data, ResponseMetadata metadata) {

    public static <T> ResponseApi<T> success (T data) {
        return new ResponseApi<>(new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE, null), data, ResponseMetadata.getCurrentMetadata());
    }

    public static <T> ResponseApi<T> error (String errorCode, String errorMessage) {
        return new ResponseApi<>(new ResponseStatus(errorCode, ERROR_MESSAGE, errorMessage), null, ResponseMetadata.getCurrentMetadata());
    }
}
